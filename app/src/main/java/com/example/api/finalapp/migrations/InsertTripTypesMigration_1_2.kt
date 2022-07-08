package com.example.api.finalapp.migrations

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

val INSERT_TRIP_TYPES_MIGRATION_1_2: Migration = object : Migration(1, 2) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL("""
            INSERT INTO TripType
                (name)
            SELECT
                nameToInsert
            WHERE NOT EXISTS (
                SELECT name nameToInsert
                FROM TripType
                WHERE name IN (
                    'Deslocamento',
                    'Lazer',
                    'Trabalho',
                    'Mudança',
                    'Somente ida'
                )
            )
        """)

//        database.execSQL("""
//            INSERT OR IGNORE INTO TripType
//                (name)
//            VALUES
//                ('Deslocamento'),
//                ('Lazer'),
//                ('Trabalho'),
//                ('Mudança'),
//                ('Somente ida')
//        """)
    }
}