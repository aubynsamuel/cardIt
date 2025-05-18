package com.aubynsamuel.cardit.models

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

enum class ProfileEnum {
    FULL_NAME,
    PHONE_NUMBER,
    EMAIL_ADDRESS,
    INSTAGRAM_HANDLE,
    TWITTER_HANDLE,
    LINKEDIN_HANDLE,
    PERSONAL_WEBSITE,
    PROFILE_URI,
}