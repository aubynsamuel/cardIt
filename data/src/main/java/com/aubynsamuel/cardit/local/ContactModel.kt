package com.aubynsamuel.cardit.local

import androidx.annotation.Keep
import androidx.room.Entity
import androidx.room.PrimaryKey

@Keep
@Entity(tableName = "recent_scans")
data class ContactModel(
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