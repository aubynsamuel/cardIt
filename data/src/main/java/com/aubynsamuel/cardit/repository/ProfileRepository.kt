package com.aubynsamuel.cardit.repository

import com.aubynsamuel.cardit.local.ProfilePreferences
import com.aubynsamuel.core.model.UserProfile
import javax.inject.Inject

class ProfileRepository @Inject constructor(
    private val profilePreferences: ProfilePreferences,
) {

    fun saveProfile(userProfile: UserProfile) {
        profilePreferences.saveProfile(userProfile)
    }

    fun loadProfile(): UserProfile {
        return profilePreferences.loadProfile()
    }
}
