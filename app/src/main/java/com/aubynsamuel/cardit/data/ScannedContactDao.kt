package com.aubynsamuel.cardit.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.aubynsamuel.cardit.models.ScannedContact
import kotlinx.coroutines.flow.Flow

@Dao
interface ScannedContactDao {
    @Insert(onConflict = OnConflictStrategy.Companion.REPLACE)
    suspend fun insertContact(contact: ScannedContact)

    @Query("SELECT * FROM recent_scans ORDER BY scannedDate DESC")
    fun getAllScannedContacts(): Flow<List<ScannedContact>>

    @Query("SELECT * FROM recent_scans WHERE id = :id")
    suspend fun getContactById(id: Int): ScannedContact?

    @Delete
    suspend fun deleteContact(contact: ScannedContact)

    @Query("DELETE FROM recent_scans")
    suspend fun clearAll()
}