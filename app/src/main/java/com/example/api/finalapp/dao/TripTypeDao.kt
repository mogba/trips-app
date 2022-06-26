package com.example.api.finalapp.dao

import androidx.room.*
import com.example.api.finalapp.model.TripType

@Dao
interface TripTypeDao : IBaseDao {
    @Insert
    suspend fun insert(tripType: TripType)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(tripType: TripType)

    @Delete
    suspend fun delete(tripType: TripType)

    @Query("select * from TripType order by id, name")
    suspend fun findAll(): List<TripType>

    @Query("select * from TripType where id = :id")
    suspend fun findById(id: Int): TripType?
}