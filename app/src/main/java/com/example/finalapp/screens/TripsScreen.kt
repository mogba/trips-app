package com.example.finalapp.screens

import android.app.Application
import android.os.Build
import androidx.annotation.RequiresApi
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
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.api.finalapp.model.Trip
import com.example.finalapp.components.Message
import com.example.finalapp.navigation.ScreenManager
import com.example.finalapp.viewmodels.TripTypeViewModel
import com.example.finalapp.viewmodels.TripTypeViewModelFactory
import com.example.finalapp.viewmodels.TripViewModel
import com.example.finalapp.viewmodels.TripViewModelFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.text.DecimalFormat

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun TripsScreen(navController: NavHostController, userId: Long) {
    Column {
        TripsList(
            userId,
            onClickListItem = { tripId ->
                navController.navigate(
                    ScreenManager.TripForm.routeWithArgs(tripId),
                )
            }
        )
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun TripsList(userId: Long, onClickListItem: (tripId: Long) -> Unit) {
    val context = LocalContext.current
    val app = context.applicationContext as Application

    var showActionDialog by remember { mutableStateOf(false) }
    var selectedTrip: Trip? by remember { mutableStateOf(null) }

    val tripTypeViewModel: TripTypeViewModel = viewModel(factory = TripTypeViewModelFactory(app))
    val tripTypes by tripTypeViewModel.findAll().observeAsState(listOf())

    if (tripTypes == null || tripTypes.isEmpty()) {
        tripTypeViewModel.insertStandardTripTypes()
    }

    val tripViewModel: TripViewModel = viewModel(factory = TripViewModelFactory(app))
    val trips by tripViewModel.findAll(userId).observeAsState(listOf())

    LazyColumn {
        items(items = trips) { trip ->
            TripListItem(
                trip,
                onTap = { tripId -> onClickListItem(tripId) },
                onLongPress = { trip ->
                    showActionDialog = true
                    selectedTrip = trip
                }
            )
        }
    }

    if (showActionDialog) {
        AlertDialog(
            onDismissRequest = {
                showActionDialog = false
                selectedTrip = null
            },
            text = {
                Text(
                    "Selecione a ação ${selectedTrip?.id} - ${selectedTrip?.destination}",
                    style = MaterialTheme.typography.h6,
                    fontWeight = FontWeight.Bold
                )
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        if (selectedTrip != null) {
                            tripViewModel.delete(selectedTrip!!)
                            Message(context, "Viagem excluída.")
                        }
                    }
                ) {
                    Text(
                        "Excluir viagem",
                        fontSize = 18.sp,
                    )
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        showActionDialog = false
                        selectedTrip = null
                    }
                ) {
                    Text(
                        "Cancelar",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            },
        )
    }
}

@Composable
fun TripListItem(
    trip: Trip,
    onTap: (Long) -> Unit,
    onLongPress: (Trip) -> Unit,
) {
    val context = LocalContext.current
    val df = DecimalFormat("0.00")

    var dateCaption = "Data de partida: ${trip.departureDate}"

    if (!trip.arrivalDate.isNullOrBlank()) {
        dateCaption += " - Data de chegada: ${trip.arrivalDate}"
    }

    Card(
        elevation = 8.dp,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp, horizontal = 8.dp)
            .pointerInput(Unit) {
                detectTapGestures(
                    onTap = {
                        onTap(trip.id)
                    },
                    onLongPress = {
                        onLongPress(trip)
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
}