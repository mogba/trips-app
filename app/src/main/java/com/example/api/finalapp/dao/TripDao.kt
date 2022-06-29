package com.example.api.finalapp.dao

import androidx.room.*
import com.example.api.finalapp.model.Trip

@Dao
interface TripDao {
    @Insert
    fun insert(trip: Trip): Long

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(trip: Trip)

    @Delete
    fun delete(trip: Trip)

    @Query("select * from Trip where userId = :userId order by id, destination")
    fun findAll(userId: Long): List<Trip>

    @Query("select * from Trip where id = :id")
    fun findById(id: Long): Trip?
}
