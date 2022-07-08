package com.example.api.finalapp.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.api.finalapp.model.Trip

@Dao
interface TripDao {
    @Insert
    suspend fun insert(trip: Trip): Long

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(trip: Trip)

    @Query("""
        select *
        from Trip
        where userId = :userId
        order by id, destination
    """)
    fun findAll(userId: Long): LiveData<List<Trip>>

    @Query("select * from Trip where id = :id")
    fun findById(id: Long): LiveData<Trip>

    @Delete
    suspend fun delete(trip: Trip)
}
