package com.aubynsamuel.cardit.models

data class UserProfile(
    val fullName: String = "",
    val phoneNumber: String = "",
    val emailAddress: String = "",
    val instagramHandle: String = "",
    val twitterHandle: String = "",
    val linkedInHandle: String = "",
    val personalWebsite: String? = null,
    val profilePhotoUri: String? = null,
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
    FULLNAME,
    PHONENUMBER,
    EMAILADDRESS,
    INSTAGRAMHANDLE,
    TWITTERHANDLE,
    LINKEDINHANDLE,
    PERSONALWEBSITE,
    PROFILEURI,
}