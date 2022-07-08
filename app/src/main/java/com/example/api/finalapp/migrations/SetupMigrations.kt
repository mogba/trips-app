package com.example.api.finalapp.migrations

import androidx.room.RoomDatabase

fun <T: RoomDatabase> SetupMigrations(
    builder: RoomDatabase.Builder<T>,
): RoomDatabase.Builder<T> {

    builder.addMigrations(
        INSERT_TRIP_TYPES_MIGRATION_1_2
    )

    return builder
}