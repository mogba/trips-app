package com.example.finalapp.screens

import android.app.Application
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
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
    val trips = listOf(
        Trip(userId = null, departureDate = LocalDate.now(), arrivalDate = LocalDate.now(), destination = "Noruega", tripTypeId = 1, budget = 1000.00),
        Trip(userId = null, departureDate = LocalDate.now(), arrivalDate = LocalDate.now(), destination = "Escandinávia", tripTypeId = 1, budget = 1000.00),
        Trip(userId = null, departureDate = LocalDate.now(), arrivalDate = LocalDate.now(), destination = "Espanha", tripTypeId = 1, budget = 1000.00),
        Trip(userId = null, departureDate = LocalDate.now(), arrivalDate = LocalDate.now(), destination = "Luxemburgo", tripTypeId = 1, budget = 1000.00),
        Trip(userId = null, departureDate = LocalDate.now(), arrivalDate = LocalDate.now(), destination = "Maldivas", tripTypeId = 1, budget = 1000.00),
        Trip(userId = null, departureDate = LocalDate.now(), arrivalDate = LocalDate.now(), destination = "Maldivas", tripTypeId = 1, budget = 1000.00),
        Trip(userId = null, departureDate = LocalDate.now(), arrivalDate = LocalDate.now(), destination = "Maldivas", tripTypeId = 1, budget = 1000.00),
        Trip(userId = null, departureDate = LocalDate.now(), arrivalDate = LocalDate.now(), destination = "Maldivas", tripTypeId = 1, budget = 1000.00),
    )

    val ctx = LocalContext.current
    val app = ctx.applicationContext as Application

    val tripViewModel: TripViewModel = viewModel(factory = TripViewModelFactory(app))

//    val trips = tripViewModel.findAll(userId)

    LazyColumn {
        items(items = trips) { trip ->
            TripListItem(
                trip,
                onClick = { onClickListItem(trip.id) },
            )
        }
    }
}

@Composable
fun TripListItem(
    trip: Trip,
    onClick: (tripId: Long) -> Unit
) {
    val context = LocalContext.current
    val df = DecimalFormat("0.00")

    Card(
        elevation = 8.dp,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp, horizontal = 8.dp)
            .clickable {
                onClick(if (trip.id == null) -1 else trip.id)
            }
    ) {
        Row() {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .weight(1f)
            ) {
                Text(text = trip.destination)
                Text(
                    text = "Data de partida: ${trip.departureDate} - Data de chegada: ${trip.arrivalDate}",
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