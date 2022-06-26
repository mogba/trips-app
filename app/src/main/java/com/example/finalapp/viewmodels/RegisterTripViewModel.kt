package com.example.finalapp.viewmodels

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.api.finalapp.model.Trip
import com.example.api.finalapp.repository.TripRepository
import kotlinx.coroutines.launch
import java.util.*

class RegisterTripViewModel(
    private val repository: TripRepository
): ViewModel() {
//    val id by mutableStateOf(-1)
    val tripTypeId by mutableStateOf(-1)
    val userId by mutableStateOf(-1)
    val destination by mutableStateOf("")
    val departureDate by mutableStateOf(Date())
    val arrivalDate by mutableStateOf(Date())
    val budget by mutableStateOf(0.0)

    fun save() {
        val trip = Trip(tripTypeId, userId, destination, departureDate, arrivalDate, budget)
        viewModelScope.launch { repository.save(trip) }
    }
}