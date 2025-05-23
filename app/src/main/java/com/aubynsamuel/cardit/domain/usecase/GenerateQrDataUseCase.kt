package com.aubynsamuel.cardit.domain.usecase

import com.aubynsamuel.cardit.domain.model.UserProfile
import com.google.gson.Gson
import javax.inject.Inject

class GenerateQrDataUseCase @Inject constructor(
    private val gson: Gson,
) {
    operator fun invoke(userProfile: UserProfile): String {
        val sharedData = mutableMapOf<String, Any>()
        sharedData["fullName"] = userProfile.fullName

        if (userProfile.sharePhoneNumber && userProfile.phoneNumber.isNotBlank())
            sharedData["phoneNumber"] = userProfile.phoneNumber
        if (userProfile.shareEmail && userProfile.emailAddress.isNotBlank())
            sharedData["emailAddress"] = userProfile.emailAddress
        if (userProfile.shareInstagram && userProfile.instagramHandle.isNotBlank())
            sharedData["instagramHandle"] = userProfile.instagramHandle
        if (userProfile.shareTwitter && userProfile.twitterHandle.isNotBlank())
            sharedData["twitterHandle"] = userProfile.twitterHandle
        if (userProfile.shareLinkedIn && userProfile.linkedInHandle.isNotBlank())
            sharedData["linkedInHandle"] = userProfile.linkedInHandle
        if (userProfile.shareWebsite && userProfile.personalWebsite.isNotBlank())
            sharedData["personalWebsite"] = userProfile.personalWebsite
        if (userProfile.shareProfilePhoto && userProfile.profilePhotoUri.isNotBlank())
            sharedData["profilePhotoUri"] = userProfile.profilePhotoUri

        return gson.toJson(sharedData)
    }
}
