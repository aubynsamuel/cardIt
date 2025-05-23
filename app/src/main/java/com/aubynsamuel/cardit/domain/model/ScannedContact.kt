package com.aubynsamuel.cardit.domain.model

data class Contact(
    val id: Int = 0,
    val fullName: String,
    val phoneNumber: String?,
    val emailAddress: String?,
    val instagramHandle: String?,
    val twitterHandle: String?,
    val linkedInHandle: String?,
    val personalWebsite: String?,
    val profilePhotoUri: String?,
    val scannedDate: Long,
)
