package com.example.finalapp.screens

import android.app.Application
import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.finalapp.components.DatePickerField
import com.example.finalapp.components.TextField
import com.example.finalapp.viewmodels.TripViewModel
import com.example.finalapp.viewmodels.TripViewModelFactory

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun TripFormScreen(navController: NavHostController, tripId: Int) {
    val context = LocalContext.current
    val app = context.applicationContext as Application

    val columnModifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 20.dp)

    val trip: TripViewModel = viewModel(factory = TripViewModelFactory(app))
    val topBarTitle = if (trip.id <= 0) "Adicionar viagem" else "Editar viagem"

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = topBarTitle) },
                navigationIcon = if (navController.previousBackStackEntry != null) {
                    {
                        IconButton(onClick = { navController.navigateUp() }) {
                            Icon(
                                imageVector = Icons.Filled.ArrowBack,
                                contentDescription = "Voltar"
                            )
                        }
                    }
                } else {
                    null
                }
            )
        }
    ) {
        Column {
            Column(modifier = columnModifier) {
                TextField(
                    value = trip.destination,
                    onChange = { trip.destination = it },
                    label = "Destino *",
                )
            }
            Column(modifier = columnModifier) {
                TextField(
                    value = "",
                    onChange = { },
                    label = "Tipo de viagem *",
                )
            }
            Column(modifier = columnModifier) {
                DatePickerField(
                    label = "Data de partida *",
                    value = trip.departureDate,
                    onChange = { trip.departureDate = it },
                )
            }
            Column(modifier = columnModifier) {
                DatePickerField(
                    label = "Data de chegada",
                    value = trip.arrivalDate,
                    onChange = { trip.arrivalDate = it },
                )
            }
            Column(modifier = columnModifier) {
                TextField(
                    label = "Orçamento",
                    value = trip.budget.toString(),
                    onChange = {
                        if (!it.isNullOrBlank()) {
                            trip.budget = it.toDouble()
                        }
                    },
                )
            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(10.dp)
            ) {
                Button(
                    onClick = {
                        if (
                            trip.destination.isNullOrBlank() ||
                            trip.tripTypeId <= 0 ||
                            trip.departureDate == null
                        ) {
                            Toast.makeText(
                                context,
                                "Preencha os campos obrigatórios",
                                Toast.LENGTH_LONG
                            ).show()
                        }
                        else {
                            trip.save()
                            Toast.makeText(
                                context,
                                "Viagem salva",
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    }
                ) {
                    Text(text = "Salvar")
                }
            }
        }
    }
}