package com.example.api.finalapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate

@Entity
data class Trip(
    val tripTypeId: Long?,
    val userId: Long?,
    val destination: String,
    val departureDate: LocalDate?,
    val arrivalDate: LocalDate?,
    val budget: Double?,
) {
    @PrimaryKey(autoGenerate = true)
    var id: Long = -1
}