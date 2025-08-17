package com.aubynsamuel.core.model

import androidx.annotation.Keep

@Keep
data class UserProfile(
    val fullName: String = "",
    val phoneNumber: String = "",
    val emailAddress: String = "",
    val instagramHandle: String = "",
    val twitterHandle: String = "",
    val linkedInHandle: String = "",
    val personalWebsite: String = "",
    val profilePhotoUri: String = "",
    // For toggling shared details
    val sharePhoneNumber: Boolean = true,
    val shareEmail: Boolean = true,
    val shareInstagram: Boolean = true,
    val shareTwitter: Boolean = true,
    val shareLinkedIn: Boolean = true,
    val shareWebsite: Boolean = true,
    val shareProfilePhoto: Boolean = true,
)