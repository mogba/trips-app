package com.example.api.finalapp.repository

import android.app.Application
import com.example.api.finalapp.dao.AppDatabaseConnection
import com.example.api.finalapp.dao.TripDao
import com.example.api.finalapp.model.IBaseModel
import com.example.api.finalapp.model.Trip

class BaseRepository<T : IBaseModel>(app: Application) {

//    private val modelDao = AppDatabaseConnection
//        .getDB(app).tripDao()

//    suspend fun create(trip: Trip): Int = tripDao.insert(trip)
//
//    suspend fun update(trip: Trip) = tripDao.update(trip)
//
//    suspend fun findAll(): List<Trip> = tripDao.findAll()
//
//    suspend fun findById(id: Int) = tripDao.findById(id)
//
//    suspend fun delete(trip: Trip) = tripDao.delete(trip)
}