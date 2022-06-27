package com.example.api.finalapp.dao

import android.content.Context
import androidx.room.*
import com.example.api.finalapp.converters.DateConverter
import com.example.api.finalapp.model.Trip
import com.example.api.finalapp.model.TripType

@Database(
    entities = [
        Trip::class,
        TripType::class
    ],
    version = 2
)
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
