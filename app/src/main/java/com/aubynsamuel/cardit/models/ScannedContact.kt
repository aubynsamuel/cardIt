package com.aubynsamuel.cardit.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "recent_scans")
data class ScannedContact(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val fullName: String,
    val phoneNumber: String?,
    val emailAddress: String?,
    val instagramHandle: String?,
    val twitterHandle: String?,
    val linkedInHandle: String?,
    val personalWebsite: String?,
    val profilePhotoUri: String?,
    val scannedDate: Long = System.currentTimeMillis(),
)