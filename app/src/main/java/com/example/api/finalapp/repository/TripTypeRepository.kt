package com.example.api.finalapp.repository

import android.app.Application
import com.example.api.finalapp.dao.AppDatabase
import com.example.api.finalapp.dao.TripTypeDao
import com.example.api.finalapp.model.TripType

class TripTypeRepository(app: Application) {

    private val tripTypeDao: TripTypeDao = AppDatabase
        .getDB(app).tripTypeDao()

    suspend fun save(tripType: TripType) {
        if (tripType.id != null && tripType.id <= 0) {
            tripTypeDao.insert(tripType)
        }
        else {
            tripTypeDao.update(tripType)
        }
    }

    suspend fun findAll(): List<TripType> = tripTypeDao.findAll()

    suspend fun findById(id: Int) = tripTypeDao.findById(id)

    suspend fun delete(tripType: TripType) = tripTypeDao.delete(tripType)
}