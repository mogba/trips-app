package com.example.api.finalapp.dao

import android.content.Context
import androidx.room.*
import com.example.api.finalapp.extensions.addMigrationsCustom
import com.example.api.finalapp.migrations.INSERT_TRIP_TYPES_MIGRATION_1_2
import com.example.api.finalapp.model.Trip
import com.example.api.finalapp.model.TripType
import com.example.api.finalapp.model.User

@Database(
    entities = [
        Trip::class,
        TripType::class,
        User::class,
    ],
    version = 2,
)
abstract class AppDatabase: RoomDatabase() {
    abstract fun tripDao(): TripDao
    abstract fun tripTypeDao(): TripTypeDao
    abstract fun authDao(): AuthDao

    companion object {
        var connection: AppDatabase? = null

        fun getDB(context: Context): AppDatabase {
            val temp = connection

            if (temp != null) {
                return temp
            }
            else {
                val instance = Room
                    .databaseBuilder(
                        context,
                        AppDatabase::class.java,
                        "trips_db_1"
                    )
//                    .addMigrationsCustom()
                    .build()
                connection = instance
                return instance
            }
        }
    }
}
