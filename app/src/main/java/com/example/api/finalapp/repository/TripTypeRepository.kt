package com.example.api.finalapp.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.api.finalapp.dao.AppDatabase
import com.example.api.finalapp.dao.TripTypeDao
import com.example.api.finalapp.model.Trip
import com.example.api.finalapp.model.TripType

class TripTypeRepository(app: Application) {
    private val tripTypeDao: TripTypeDao = AppDatabase
        .getDB(app).tripTypeDao()

    suspend fun create(tripType: TripType) = tripTypeDao.insert(tripType)

    suspend fun update(tripType: TripType) = tripTypeDao.update(tripType)

    fun findAll(): LiveData<List<TripType>> = tripTypeDao.findAll()

    suspend fun findById(id: Int) = tripTypeDao.findById(id)

    suspend fun delete(tripType: TripType) = tripTypeDao.delete(tripType)
}