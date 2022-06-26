package com.example.finalapp.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController

@Composable
fun TripFormScreen(navController: NavHostController, tripId: Int) {
    Column {
        Text("Form de viagem")
        Text(tripId.toString())

        Button(
            onClick = {
                navController.navigateUp()
            }
        ) {
            Text(text = "Voltar")
        }
    }
}