package com.example.finalapp.viewmodels

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.api.finalapp.repository.AuthRepository

class UserViewModelFactory(
    private val app: Application
): ViewModelProvider.Factory {
    override fun <T: ViewModel> create(modelClass: Class<T>): T {
        val repository = AuthRepository(app)
        val model = UserViewModel(repository)
        return model as T
    }
}