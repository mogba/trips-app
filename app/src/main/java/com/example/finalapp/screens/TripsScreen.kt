package com.example.finalapp.screens

import android.app.Application
import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.api.finalapp.model.Trip
import com.example.finalapp.navigation.ScreenManager
import com.example.finalapp.viewmodels.TripViewModel
import com.example.finalapp.viewmodels.TripViewModelFactory
import java.text.DecimalFormat
import java.time.LocalDate

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun TripsScreen(navController: NavHostController, userId: Long) {
    Column {
        TripsList(
            userId,
            onClickListItem = { tripId ->
                navController.navigate(ScreenManager.TripForm.routeWithArgs(tripId))
            }
        )
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun TripsList(userId: Long, onClickListItem: (tripId: Long) -> Unit) {
    val ctx = LocalContext.current
    val app = ctx.applicationContext as Application

    val tripViewModel: TripViewModel = viewModel(factory = TripViewModelFactory(app))

    val trips by tripViewModel.findAll(userId).observeAsState(listOf());

    LazyColumn {
        items(items = trips) { trip ->
            TripListItem(
                tripViewModel,
                trip,
                onClick = { onClickListItem(trip.id) },
            )
        }
    }
}

@Composable
fun TripListItem(
    tripViewModel: TripViewModel,
    trip: Trip,
    onClick: (tripId: Long) -> Unit
) {
    val context = LocalContext.current
    val df = DecimalFormat("0.00")

    var toDelete by remember { mutableStateOf(false) }
    val showActionDialog = remember { mutableStateOf(true) }

    var dateCaption = "Data de partida: ${trip.departureDate}"

    if (!trip.arrivalDate.isNullOrBlank()) {
        dateCaption += "- Data de chegada: ${trip.arrivalDate}"
    }

    Card(
        elevation = 8.dp,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp, horizontal = 8.dp)
            .clickable {
                onClick(if (trip.id == null) -1 else trip.id)
            }
            .pointerInput(Unit) {
                detectTapGestures(
                    onTap = {
//                        navController.navigate("despesas/" + viagem.id + "/" + viagem.destino)
                    },
                    onLongPress = {
                        toDelete = true;
                    },
                )
            }
    ) {
        Row {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .weight(1f)
            ) {
                Text(text = trip.destination)
                Text(
                    text = dateCaption,
                    style = MaterialTheme.typography.caption
                )
            }
            Text(
                text = "R$ ${df.format(trip.budget)}",
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .padding(16.dp)
            )
        }
    }

    if (toDelete) {
        AlertDialog(
            onDismissRequest = { showActionDialog.value = false },
            text = {
                Text(
                    "Qual ação deseja?",
                    style = MaterialTheme.typography.h6,
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        tripViewModel.delete(trip)
                        Toast
                            .makeText(
                                context,
                                "Viagem apagada!",
                                Toast.LENGTH_SHORT
                            )
                            .show()
                        toDelete = false
                    }
                ) {
                    Text(
                        "Excluir viagem!", fontSize = 18.sp,
                        color = Color.White
                    )
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        showActionDialog.value = false
                        toDelete = false
                    }
                ) {
                    Text(
                        "Nada",
                        color = Color.White,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
                TextButton(
                    onClick = {
                        showActionDialog.value = false
                        toDelete = false
                        onClick(trip.id)
                    }
                ) {
                    Text(
                        "Editar viagem",
                        color = Color.White,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            },
        )
    }
}