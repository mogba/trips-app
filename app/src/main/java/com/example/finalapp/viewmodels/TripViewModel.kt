package com.example.finalapp.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.api.finalapp.model.Trip
import com.example.api.finalapp.repository.TripRepository
import kotlinx.coroutines.launch
import java.time.LocalDate

class TripViewModel(
    private val repository: TripRepository
): ViewModel() {
    var id by mutableStateOf(0L)
    var tripTypeId by mutableStateOf(0L)
    var userId by mutableStateOf(0L)
    var destination by mutableStateOf("")
    var departureDate by mutableStateOf("")
    var arrivalDate by mutableStateOf("")
    var budget by mutableStateOf(0.0)

    fun save() {
        val trip = Trip(
            tripTypeId,
            userId,
            destination,
            departureDate,
            arrivalDate,
            budget,
        )

        viewModelScope.launch {
            if (trip.id <= 0) {
                repository.create(trip)
            }
            else {
                repository.update(trip)
            }
        }
    }

    fun findAll(userId: Long): LiveData<List<Trip>> = repository.findAll(userId)

//    fun loadTrip(id: Long) = viewModelScope.launch {
//        var trip = repository.findById(id)
//        this@TripViewModel.id = trip!!.id
//        this@TripViewModel.tripTypeId = trip!!.tripTypeId!!
//        this@TripViewModel.destination = trip!!.destination
//        this@TripViewModel.userId = trip!!.userId!!
//        this@TripViewModel.departureDate = trip!!.departureDate
//        this@TripViewModel.arrivalDate = trip!!.arrivalDate!!
//        this@TripViewModel.budget = trip!!.budget!!
//    }

    fun findById(id: Long): LiveData<Trip> = repository.findById(id)

    fun delete(trip: Trip) = viewModelScope.launch { repository.delete(trip) }

    fun isValidForCreate() =
        !(
            destination.isNullOrBlank() ||
            tripTypeId <= 0 ||
            departureDate.isNullOrBlank()
        )
}