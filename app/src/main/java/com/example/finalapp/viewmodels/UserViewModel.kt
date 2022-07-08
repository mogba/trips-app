package com.example.finalapp.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.api.finalapp.model.User
import com.example.api.finalapp.repository.AuthRepository
import kotlinx.coroutines.launch

class UserViewModel(
    private val repository: AuthRepository,
) : ViewModel() {
    var id by mutableStateOf(0L)
    var name by mutableStateOf("")
    var email by mutableStateOf("")
    var password by mutableStateOf("")
    var isSessionActive by mutableStateOf(false)

    fun login(onSuccess: (User?) -> Unit) = viewModelScope.launch {
        val user = repository.login(email, password)
        id = user?.id ?: 0
        name = user?.name ?: ""
        email = user?.email ?: ""
        onSuccess(user)
    }

    fun register() = viewModelScope.launch {
        repository.register(User(name, email, password))
    }

    fun sendResetPasswordEmail() {}

    fun isValidForLogin() =
        !(
            email.isNullOrBlank() ||
            password.isNullOrBlank()
        )

    fun isValidForRegister() =
        !(
            name.isNullOrBlank() ||
            email.isNullOrBlank() ||
            password.isNullOrBlank()
        )
}