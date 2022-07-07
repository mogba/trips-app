package com.example.api.finalapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TripType(
    val name: String
) {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
}