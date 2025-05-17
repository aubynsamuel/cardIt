package com.aubynsamuel.cardit.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.aubynsamuel.cardit.data.AppDatabase
import com.aubynsamuel.cardit.models.ScannedContact
import com.aubynsamuel.cardit.models.UserProfile
import com.google.gson.Gson
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class RecentScansViewModel(application: Application) : AndroidViewModel(application) {
    private val dao = AppDatabase.Companion.getDatabase(application).scannedContactDao()
    private val gson = Gson()

    val recentScans: StateFlow<List<ScannedContact>> = dao.getAllScannedContacts()
        .stateIn(viewModelScope, SharingStarted.Companion.Lazily, emptyList())

    fun saveScannedContact(qrData: String) {
        viewModelScope.launch {
            try {
                val userProfile = gson.fromJson(qrData, UserProfile::class.java)

                val scannedContact = ScannedContact(
                    fullName = userProfile.fullName,
                    phoneNumber = if (userProfile.sharePhoneNumber) userProfile.phoneNumber else null,
                    emailAddress = if (userProfile.shareEmail) userProfile.emailAddress else null,
                    instagramHandle = if (userProfile.shareInstagram) userProfile.instagramHandle else null,
                    twitterHandle = if (userProfile.shareTwitter) userProfile.twitterHandle else null,
                    linkedInHandle = if (userProfile.shareLinkedIn) userProfile.linkedInHandle else null,
                    personalWebsite = if (userProfile.shareWebsite) userProfile.personalWebsite else null,
                    profilePhotoUri = if (userProfile.shareProfilePhoto) userProfile.profilePhotoUri else null
                )
                dao.insertContact(scannedContact)
            } catch (e: Exception) {
                println("Error parsing QR data: ${e.message}")
            }
        }
    }

    fun deleteScan(contact: ScannedContact) {
        viewModelScope.launch {
            dao.deleteContact(contact)
        }
    }

    fun clearAllScans() {
        viewModelScope.launch {
            dao.clearAll()
        }
    }
}