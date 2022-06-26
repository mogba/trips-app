package com.example.api.finalapp.dao

import androidx.room.*
import com.example.api.finalapp.model.Trip

@Dao
interface TripDao : IBaseDao {
    @Insert
    suspend fun insert(trip: Trip): Int

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(trip: Trip)

    @Delete
    suspend fun delete(trip: Trip)

    @Query("select * from Trip order by id, destination")
    suspend fun findAll(): List<Trip>

    @Query("select * from Trip where id = :id")
    suspend fun findById(id: Int): Trip?
}
