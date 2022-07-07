package com.example.finalapp.screens

import android.app.Application
import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.text.isDigitsOnly
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.finalapp.components.*
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

    val tripTypeOptions = listOf("Option 1", "Option 2", "Option 3", "Option 4", "Option 5")
    var selectedTripType by remember { mutableStateOf("") }

    val trip: TripViewModel = viewModel(factory = TripViewModelFactory(app))
    val topBarTitle = if (trip.id <= 0) "Adicionar viagem" else "Editar viagem"

    Scaffold(
        topBar = {
            NavigationTopAppBar(navController, topBarTitle)
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
                DropDownField(
                    label = "Tipo de viagem *",
                    options = tripTypeOptions,
                    value = selectedTripType,
                    onValueChange = {
                        // trip.tripTypeId = it
                        selectedTripType = it
                    }
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
                        val newValue = it.toDoubleOrNull()
                        if (!it.isNullOrBlank() && newValue != null) {
                            trip.budget = it.toDouble()
                        }
                    },
                    isNumberInput = true,
                )
            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = columnModifier
            ) {
                Button(
                    onClick = {
                        if (
                            trip.destination.isNullOrBlank() ||
//                            trip.tripTypeId <= 0 ||
                            trip.departureDate == null
                        ) {
                            Message(
                                context,
                                "Preencha os campos obrigatórios",
                            )
                        }
                        else {
                            trip.save()

                            Message(
                                context,
                                "Viagem salva",
                            )
                        }
                    },
                    modifier = Modifier
                        .height(65.dp)
                        .fillMaxWidth()
                        .padding(top = 10.dp)
                ) {
                    Text(text = "Salvar")
                }
            }
        }
    }
}