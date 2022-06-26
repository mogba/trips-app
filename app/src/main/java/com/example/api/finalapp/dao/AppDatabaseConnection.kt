package com.example.api.finalapp.dao

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.api.finalapp.model.Trip

@Database(entities = arrayOf(Trip::class), version = 1 )
abstract class AppDatabaseConnection: RoomDatabase() {

    abstract fun tripDao(): TripDao
    abstract fun tripTypeDao(): TripTypeDao

    // Desing Pattern - Singleton
    companion object {
        var connection: AppDatabaseConnection? = null

        fun getDB(context: Context): AppDatabaseConnection {
            val temp = connection

            if (temp != null) {
                return temp
            }
            else {
                val instance = Room.databaseBuilder(
                    context,
                    AppDatabaseConnection::class.java,
                    "meu-database"
                ).build()
                connection = instance
                return instance
            }
        }
    }
}
