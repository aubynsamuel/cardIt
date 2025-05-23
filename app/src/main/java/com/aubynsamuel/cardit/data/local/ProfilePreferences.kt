package com.aubynsamuel.cardit.data.local

import android.content.Context
import androidx.core.content.edit
import com.aubynsamuel.cardit.domain.model.UserProfile
import com.google.gson.Gson
import javax.inject.Inject

class ProfilePreferences @Inject constructor(context: Context) {

    private val sharedPreferences = context.getSharedPreferences(
        "user_profile_prefs",
        Context.MODE_PRIVATE
    )
    private val gson = Gson()

    fun saveProfile(userProfile: UserProfile) {
        val profileJson = gson.toJson(userProfile)
        sharedPreferences.edit { putString("profile_data", profileJson) }
    }

    fun loadProfile(): UserProfile {
        val profileJson = sharedPreferences.getString("profile_data", null)
        return if (profileJson != null) {
            gson.fromJson(profileJson, UserProfile::class.java)
        } else {
            UserProfile() // Return default if nothing is saved
        }
    }
}
