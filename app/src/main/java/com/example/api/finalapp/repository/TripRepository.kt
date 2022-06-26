package com.example.api.finalapp.repository

import android.app.Application
import com.example.api.finalapp.dao.AppDatabaseConnection
import com.example.api.finalapp.dao.TripDao
import com.example.api.finalapp.model.Trip

class TripRepository(app: Application) {

    private val tripDao: TripDao = AppDatabaseConnection
        .getDB(app).tripDao()

    suspend fun save(trip: Trip) {
        if (trip.id == 0) {
            tripDao.insert(trip)
        }
        else {
            tripDao.update(trip)
        }
    }

    suspend fun findAll(): List<Trip> = tripDao.findAll()

    suspend fun findById(id: Int) = tripDao.findById(id)

    suspend fun delete(trip: Trip) = tripDao.delete(trip)
}