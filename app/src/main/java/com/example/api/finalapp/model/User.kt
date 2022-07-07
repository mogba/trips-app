package com.example.api.finalapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    val name: String,
    val email: String,
    val password: String,
) {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
}