package com.example.api.finalapp.extensions

import androidx.room.RoomDatabase
import com.example.api.finalapp.migrations.SetupMigrations

fun <T: RoomDatabase> RoomDatabase.Builder<T>.addMigrationsCustom(): RoomDatabase.Builder<T> {
    SetupMigrations(this)
    return this
}