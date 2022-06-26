package com.example.api.finalapp.model

import androidx.room.Entity

@Entity
data class TripType(
    val id: Int?,
    val name: String
) { }