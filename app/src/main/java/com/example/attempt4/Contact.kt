package com.example.attempt4

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Contact(
    val dateToday: String,
    val timeToday: String,
    val waterIntake: String,
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0
)
