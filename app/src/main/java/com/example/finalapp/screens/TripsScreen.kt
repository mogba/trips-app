package com.example.finalapp.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.api.finalapp.model.Trip
import com.example.finalapp.navigation.ScreenManager
import java.text.DecimalFormat
import java.util.*

@Composable
fun TripsScreen(navController: NavHostController, userId: Int) {
    Column {
        TripsList(onClickListItem = { tripId ->
            navController.navigate(ScreenManager.TripForm.routeWithArgs(tripId))
        })
    }
}

@Composable
fun TripsList(onClickListItem: (tripId: Int) -> Unit) {
    val trips = listOf(
        Trip(userId = null, departureDate = Date(), arrivalDate = Date(), destination = "Noruega", tripTypeId = 1, budget = 1000.00),
        Trip(userId = null, departureDate = Date(), arrivalDate = Date(), destination = "Escandinávia", tripTypeId = 1, budget = 1000.00),
        Trip(userId = null, departureDate = Date(), arrivalDate = Date(), destination = "Espanha", tripTypeId = 1, budget = 1000.00),
        Trip(userId = null, departureDate = Date(), arrivalDate = Date(), destination = "Luxemburgo", tripTypeId = 1, budget = 1000.00),
        Trip(userId = null, departureDate = Date(), arrivalDate = Date(), destination = "Maldivas", tripTypeId = 1, budget = 1000.00),
        Trip(userId = null, departureDate = Date(), arrivalDate = Date(), destination = "Maldivas", tripTypeId = 1, budget = 1000.00),
        Trip(userId = null, departureDate = Date(), arrivalDate = Date(), destination = "Maldivas", tripTypeId = 1, budget = 1000.00),
        Trip(userId = null, departureDate = Date(), arrivalDate = Date(), destination = "Maldivas", tripTypeId = 1, budget = 1000.00),
    )

    LazyColumn {
        items(items = trips) { trip ->
            TripListItem(
                trip,
                onClick = onClickListItem,
            )
        }
    }
}

@Composable
fun TripListItem(
    trip: Trip,
    onClick: (tripId: Int) -> Unit
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