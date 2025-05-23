package com.aubynsamuel.cardit.data.repository

import com.aubynsamuel.cardit.data.local.ContactDao
import com.aubynsamuel.cardit.data.mappers.toData
import com.aubynsamuel.cardit.data.mappers.toDomain
import com.aubynsamuel.cardit.data.mappers.toScannedContactModel
import com.aubynsamuel.cardit.domain.model.Contact
import com.aubynsamuel.cardit.domain.model.UserProfile
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ContactRepository @Inject constructor(
    private val dao: ContactDao,
    private val gson: Gson,
) {

    fun getAllContacts(): Flow<List<Contact>> {
        return dao.getAllContacts().map { list ->
            list.map { it.toDomain() }
        }
    }

    suspend fun saveContact(qrData: String): Boolean {
        return try {
            val userProfile = gson.fromJson(qrData, UserProfile::class.java)
            val scannedContactModel = userProfile.toScannedContactModel()
            dao.insertContact(scannedContactModel)
            true
        } catch (e: JsonSyntaxException) {
            println("Error parsing QR data: ${e.message}")
            false
        } catch (e: Exception) {
            println("Error saving scanned contact: ${e.message}")
            false
        }
    }

    suspend fun deleteContact(contact: Contact) {
        dao.deleteContact(contact.toData())
    }

    suspend fun clearAll() {
        dao.clearAll()
    }
}
