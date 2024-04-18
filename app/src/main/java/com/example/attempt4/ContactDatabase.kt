package com.example.attempt4

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [Contact::class],
    version = 2
)
abstract class ContactDatabase: RoomDatabase() {

    abstract val dao: ContactDao
}