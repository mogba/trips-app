package com.example.api.finalapp.repository

import android.app.Application
import com.example.api.finalapp.dao.AppDatabaseConnection
import com.example.api.finalapp.dao.TripDao
import com.example.api.finalapp.model.Trip

class TripRepository(app: Application) {

    private val tripDao: TripDao = AppDatabaseConnection
        .getDB(app).tripDao()

    fun create(trip: Trip): Long = tripDao.insert(trip)

    fun update(trip: Trip) = tripDao.update(trip)

    fun findAll(userId: Long): List<Trip> = tripDao.findAll(userId)

    fun findById(id: Long) = tripDao.findById(id)

    fun delete(trip: Trip) = tripDao.delete(trip)
}