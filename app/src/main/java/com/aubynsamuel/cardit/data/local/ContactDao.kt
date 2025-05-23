package com.aubynsamuel.cardit.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ContactDao {
    @Insert(onConflict = OnConflictStrategy.Companion.REPLACE)
    suspend fun insertContact(contact: ContactModel)

    @Query("SELECT * FROM recent_scans ORDER BY scannedDate DESC")
    fun getAllContacts(): Flow<List<ContactModel>>

    @Query("SELECT * FROM recent_scans WHERE id = :id")
    suspend fun getContactById(id: Int): ContactModel?

    @Delete
    suspend fun deleteContact(contact: ContactModel)

    @Query("DELETE FROM recent_scans")
    suspend fun clearAll()
}