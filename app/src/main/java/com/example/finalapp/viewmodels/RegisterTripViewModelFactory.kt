package com.example.finalapp.viewmodels

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.api.finalapp.repository.TripRepository

class RegisterTripViewModelFactory(
    val app: Application
): ViewModelProvider.Factory {
    override fun <T: ViewModel> create(modelClass: Class<T>): T {
        val repository = TripRepository(app)
        val model = RegisterTripViewModel(repository)
        return model as T
    }
}
