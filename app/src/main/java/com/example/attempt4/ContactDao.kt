package com.example.attempt4

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface ContactDao {

    @Upsert
    suspend fun upsertContact(contact: Contact)

    @Delete
    suspend fun deleteContact(contact: Contact)

    @Query("SELECT * FROM contact ORDER BY dateToday ASC")
    fun getContactsOrderedByDateToday(): Flow<List<Contact>>

    @Query("SELECT * FROM contact ORDER BY timeToday ASC")
    fun getContactsOrderedByTimeToday(): Flow<List<Contact>>

    @Query("SELECT * FROM contact ORDER BY waterIntake ASC")
    fun getContactsOrderedByWaterIntake(): Flow<List<Contact>>
}