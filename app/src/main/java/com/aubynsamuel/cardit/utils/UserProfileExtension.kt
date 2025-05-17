package com.aubynsamuel.cardit.utils

import com.aubynsamuel.cardit.models.UserProfile

fun UserProfile.toJsonString(): String {
    val parts = mutableListOf<String>()
    parts.add("\"fullName\":\"$fullName\"")
    if (sharePhoneNumber && phoneNumber.isNotBlank()) parts.add("\"phoneNumber\":\"$phoneNumber\"")
    if (shareEmail && emailAddress.isNotBlank()) parts.add("\"emailAddress\":\"$emailAddress\"")
    if (shareInstagram && instagramHandle.isNotBlank()) parts.add("\"instagramHandle\":\"$instagramHandle\"")
    if (shareTwitter && twitterHandle.isNotBlank()) parts.add("\"twitterHandle\":\"$twitterHandle\"")
    if (shareLinkedIn && linkedInHandle.isNotBlank()) parts.add("\"linkedInHandle\":\"$linkedInHandle\"")
    if (shareWebsite && personalWebsite?.isNotBlank() == true) parts.add("\"personalWebsite\":\"$personalWebsite\"")
    if (shareProfilePhoto && profilePhotoUri != null) parts.add("\"profilePhotoUri\":\"$profilePhotoUri\"") // Share URI
    return "{${parts.joinToString(",")}}"
}