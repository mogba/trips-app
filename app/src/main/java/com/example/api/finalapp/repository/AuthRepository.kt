package com.example.api.finalapp.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.api.finalapp.dao.AppDatabase
import com.example.api.finalapp.dao.AuthDao
import com.example.api.finalapp.model.User
import kotlinx.coroutines.runBlocking

class AuthRepository(app: Application) {
    private val authDao: AuthDao = AppDatabase
        .getDB(app).authDao()

    suspend fun login(email: String, password: String): User? =
        authDao.login(email, password)

    suspend fun register(user: User) = authDao.register(user)
}