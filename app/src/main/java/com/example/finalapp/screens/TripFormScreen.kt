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
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.text.isDigitsOnly
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.api.finalapp.model.Trip
import com.example.api.finalapp.model.TripType
import com.example.finalapp.components.*
import com.example.finalapp.viewmodels.TripTypeViewModel
import com.example.finalapp.viewmodels.TripTypeViewModelFactory
import com.example.finalapp.viewmodels.TripViewModel
import com.example.finalapp.viewmodels.TripViewModelFactory

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun TripFormScreen(navController: NavHostController, tripId: Long?) {
    val context = LocalContext.current
    val app = context.applicationContext as Application

    val tripTypeModel: TripTypeViewModel = viewModel(factory = TripTypeViewModelFactory(app))
    val tripTypes by tripTypeModel.findAll().observeAsState(listOf())
    var selectedTripType: TripType? by remember { mutableStateOf(null) }

    val tripModel: TripViewModel = viewModel(factory = TripViewModelFactory(app))

    val _tripId = tripId ?: 0

    if (_tripId > 0) {
        val editTrip by tripModel.findById(_tripId).observeAsState(null)

        if (editTrip != null) {
            tripModel.id = editTrip?.id ?: 0
            tripModel.tripTypeId = editTrip?.tripTypeId ?: 0
            selectedTripType = tripTypes.find { tripType -> tripType.id == tripModel.tripTypeId }
            tripModel.destination = editTrip?.destination ?: ""
            tripModel.userId = editTrip?.userId ?: 0
            tripModel.departureDate = editTrip?.departureDate ?: ""
            tripModel.arrivalDate = editTrip?.arrivalDate ?: ""
            tripModel.budget = editTrip?.budget ?: 0.0
        }
    }

    val topBarTitle = if (tripModel.id <= 0) "Adicionar viagem" else "Editar viagem"

    val columnModifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 20.dp)

    Scaffold(
        topBar = {
            NavigationTopAppBar(navController, topBarTitle)
        }
    ) {
        Column {
            Column(modifier = columnModifier) {
                TextField(
                    value = tripModel.destination,
                    onChange = { tripModel.destination = it },
                    label = "Destino *",
                )
            }
            Column(modifier = columnModifier) {
                DropDownField(
                    label = "Tipo de viagem *",
                    options = tripTypes.distinctBy { tripType ->
                        tripType.name
                    },
                    value = selectedTripType,
                    onValueChange = {
                        selectedTripType = it
                        tripModel.tripTypeId = selectedTripType!!.id
                    }
                )
            }
            Column(modifier = columnModifier) {
                DatePickerField(
                    label = "Data de partida *",
                    value = tripModel.departureDate,
                    onChange = { tripModel.departureDate = it },
                )
            }
            Column(modifier = columnModifier) {
                DatePickerField(
                    label = "Data de chegada",
                    value = tripModel.arrivalDate,
                    onChange = { tripModel.arrivalDate = it },
                )
            }
            Column(modifier = columnModifier) {
                TextField(
                    label = "Orçamento",
                    value = tripModel.budget.toString(),
                    onChange = {
                        val newValue = it.toDoubleOrNull()
                        if (!it.isNullOrBlank() && newValue != null) {
                            tripModel.budget = it.toDouble()
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
                        if (tripModel.isValidForCreate()) {
                            tripModel.save()
                            Message(context, "Viagem salva")
                        } else {
                            Message(context, "Preencha os campos obrigatórios")
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