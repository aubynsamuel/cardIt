package com.aubynsamuel.cardit.repository

import com.aubynsamuel.cardit.local.ContactDao
import com.aubynsamuel.cardit.mappers.toData
import com.aubynsamuel.cardit.mappers.toDomain
import com.aubynsamuel.cardit.mappers.toScannedContactModel
import com.aubynsamuel.core.model.Contact
import com.aubynsamuel.core.model.UserProfile
import com.aubynsamuel.core.utils.validateContactDetails
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
            val isValidContact = validateContactDetails(userProfile)
            if (isValidContact) {
                val scannedContactModel = userProfile.toScannedContactModel()
                dao.insertContact(scannedContactModel)
                true
            } else {
                false
            }
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
