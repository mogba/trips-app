package com.example.finalapp.viewmodels

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.api.finalapp.model.Trip
import com.example.api.finalapp.repository.TripRepository
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.util.*

class RegisterTripViewModel(
    private val repository: TripRepository
): ViewModel() {
    var id by mutableStateOf(-1)
    var tripTypeId by mutableStateOf(-1)
    var userId by mutableStateOf(-1)
    var destination by mutableStateOf("")
    var departureDate by mutableStateOf<LocalDate?>(null)
    var arrivalDate by mutableStateOf<LocalDate?>(null)
    var budget by mutableStateOf(0.0)

    fun save() {
        val trip = Trip(tripTypeId, userId, destination, departureDate, arrivalDate, budget)
        viewModelScope.launch {
            if (trip.id <= 0) {
                id = repository.create(trip)
            }
            else {
                repository.update(trip)
            }
        }
    }
}