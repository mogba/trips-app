package com.example.api.finalapp.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.api.finalapp.dao.AppDatabase
import com.example.api.finalapp.dao.TripDao
import com.example.api.finalapp.model.Trip

class TripRepository(app: Application) {

    private val tripDao: TripDao = AppDatabase
        .getDB(app).tripDao()

    suspend fun create(trip: Trip): Long = tripDao.insert(trip)

    suspend fun update(trip: Trip) = tripDao.update(trip)

    fun findAll(userId: Long): LiveData<List<Trip>> = tripDao.findAll(userId)

    suspend fun findById(id: Long) = tripDao.findById(id)

    suspend fun delete(trip: Trip) = tripDao.delete(trip)
}