package com.example.api.finalapp.repository

import android.app.Application
import com.example.api.finalapp.dao.AppDatabaseConnection
import com.example.api.finalapp.dao.TripDao
import com.example.api.finalapp.model.Trip

class TripRepository(app: Application) {

    private val tripDao: TripDao = AppDatabaseConnection
        .getDB(app).tripDao()

    fun create(trip: Trip): Int = tripDao.insert(trip)

    fun update(trip: Trip) = tripDao.update(trip)

    fun findAll(userId: Int): List<Trip> = tripDao.findAll(userId)

    fun findById(id: Int) = tripDao.findById(id)

    fun delete(trip: Trip) = tripDao.delete(trip)
}