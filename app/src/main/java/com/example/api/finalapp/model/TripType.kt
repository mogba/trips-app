package com.example.api.finalapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TripType(
    val name: String
) : IBaseModel {
    @PrimaryKey(autoGenerate = true)
    var id: Int = -1
}