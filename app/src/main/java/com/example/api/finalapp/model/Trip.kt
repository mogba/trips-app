package com.example.api.finalapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class Trip(
    val tripTypeId: Int?,
    val userId: Int?,
    val destination: String,
    val departureDate: Date,
    val arrivalDate: Date,
    val budget: Double,
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}