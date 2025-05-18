package com.aubynsamuel.cardit.viewmodels

import android.app.Application
import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.core.content.edit
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.aubynsamuel.cardit.models.ProfileEnum
import com.aubynsamuel.cardit.models.UserProfile
import com.google.gson.Gson
import kotlinx.coroutines.launch

class ProfileViewModel(application: Application) : AndroidViewModel(application) {
    private val sharedPreferences = application.getSharedPreferences(
        "user_profile_prefs",
        Context.MODE_PRIVATE
    )
    private val gson = Gson()

    var userProfile by mutableStateOf(loadProfile())
        private set

    fun updateProfileDetails(detail: ProfileEnum, value: String) {
        userProfile = when (detail) {
            ProfileEnum.FULL_NAME -> userProfile.copy(fullName = value)
            ProfileEnum.PHONE_NUMBER -> userProfile.copy(phoneNumber = value)
            ProfileEnum.EMAIL_ADDRESS -> userProfile.copy(emailAddress = value)
            ProfileEnum.INSTAGRAM_HANDLE -> userProfile.copy(instagramHandle = value)
            ProfileEnum.TWITTER_HANDLE -> userProfile.copy(twitterHandle = value)
            ProfileEnum.LINKEDIN_HANDLE -> userProfile.copy(linkedInHandle = value)
            ProfileEnum.PERSONAL_WEBSITE -> userProfile.copy(personalWebsite = value)
            ProfileEnum.PROFILE_URI -> userProfile.copy(profilePhotoUri = value)
        }
    }

    fun toggleShareDetail(detail: String, enabled: Boolean) {
        userProfile = when (detail) {
            "phoneNumber" -> userProfile.copy(sharePhoneNumber = enabled)
            "emailAddress" -> userProfile.copy(shareEmail = enabled)
            "instagram" -> userProfile.copy(shareInstagram = enabled)
            "twitter" -> userProfile.copy(shareTwitter = enabled)
            "linkedIn" -> userProfile.copy(shareLinkedIn = enabled)
            "website" -> userProfile.copy(shareWebsite = enabled)
            "profilePhoto" -> userProfile.copy(shareProfilePhoto = enabled)
            else -> userProfile
        }
    }

    fun saveProfile() {
        viewModelScope.launch {
            val profileJson = gson.toJson(userProfile)
            sharedPreferences.edit { putString("profile_data", profileJson) }
        }
    }

    private fun loadProfile(): UserProfile {
        val profileJson = sharedPreferences.getString("profile_data", null)
        return if (profileJson != null) {
            gson.fromJson(profileJson, UserProfile::class.java)
        } else {
            UserProfile()
        }
    }
}