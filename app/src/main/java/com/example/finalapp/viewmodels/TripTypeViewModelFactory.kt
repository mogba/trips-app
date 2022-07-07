package com.example.finalapp.viewmodels

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.api.finalapp.repository.TripTypeRepository

class TripTypeViewModelFactory(
    private val app: Application
): ViewModelProvider.Factory {
    override fun <T: ViewModel> create(modelClass: Class<T>): T {
        val repository = TripTypeRepository(app)
        val model = TripTypeViewModel(repository)
        return model as T
    }
}