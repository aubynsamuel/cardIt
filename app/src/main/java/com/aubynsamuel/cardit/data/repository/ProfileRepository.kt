package com.aubynsamuel.cardit.data.repository

import com.aubynsamuel.cardit.data.local.ProfilePreferences
import com.aubynsamuel.cardit.domain.model.UserProfile
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
