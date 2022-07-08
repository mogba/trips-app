package com.example.finalapp.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.api.finalapp.model.TripType
import com.example.api.finalapp.repository.TripTypeRepository
import kotlinx.coroutines.launch

class TripTypeViewModel(
    private val repository: TripTypeRepository
): ViewModel() {
    var id by mutableStateOf(0L)
    var name by mutableStateOf("")

    fun save() {
        val tripType = TripType(name)

        viewModelScope.launch {
            if (tripType.id <= 0) {
                repository.create(tripType)
            } else {
                repository.update(tripType)
            }
        }
    }

    fun findAll(): LiveData<List<TripType>> = repository.findAll()

    fun delete(tripType: TripType) =
        viewModelScope.launch { repository.delete(tripType) }

    fun insertStandardTripTypes() = viewModelScope.launch {
        repository.insertStandardTripTypes()
    }
}