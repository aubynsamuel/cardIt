package com.aubynsamuel.cardit.data.mappers

import com.aubynsamuel.cardit.data.local.ContactModel
import com.aubynsamuel.cardit.domain.model.Contact
import com.aubynsamuel.cardit.domain.model.UserProfile

fun ContactModel.toDomain(): Contact {
    return Contact(
        id = this.id,
        fullName = this.fullName,
        phoneNumber = this.phoneNumber,
        emailAddress = this.emailAddress,
        instagramHandle = this.instagramHandle,
        twitterHandle = this.twitterHandle,
        linkedInHandle = this.linkedInHandle,
        personalWebsite = this.personalWebsite,
        profilePhotoUri = this.profilePhotoUri,
        scannedDate = this.scannedDate
    )
}

fun Contact.toData(): ContactModel {
    return ContactModel(
        id = this.id,
        fullName = this.fullName,
        phoneNumber = this.phoneNumber,
        emailAddress = this.emailAddress,
        instagramHandle = this.instagramHandle,
        twitterHandle = this.twitterHandle,
        linkedInHandle = this.linkedInHandle,
        personalWebsite = this.personalWebsite,
        profilePhotoUri = this.profilePhotoUri,
        scannedDate = this.scannedDate
    )
}

fun UserProfile.toScannedContactModel(): ContactModel {
    return ContactModel(
        fullName = this.fullName,
        phoneNumber = if (this.sharePhoneNumber) this.phoneNumber else null,
        emailAddress = if (this.shareEmail) this.emailAddress else null,
        instagramHandle = if (this.shareInstagram) this.instagramHandle else null,
        twitterHandle = if (this.shareTwitter) this.twitterHandle else null,
        linkedInHandle = if (this.shareLinkedIn) this.linkedInHandle else null,
        personalWebsite = if (this.shareWebsite) this.personalWebsite else null,
        profilePhotoUri = if (this.shareProfilePhoto) this.profilePhotoUri else null
    )
}
