package com.aubynsamuel.core.utils

import com.aubynsamuel.core.model.UserProfile


fun validateContactDetails(profile: UserProfile): Boolean {
    val hasAnyData = (profile.sharePhoneNumber && profile.phoneNumber.isNotBlank()) ||
            (profile.shareEmail && profile.emailAddress.isNotBlank()) ||
            (profile.shareInstagram && profile.instagramHandle.isNotBlank()) ||
            (profile.shareTwitter && profile.twitterHandle.isNotBlank()) ||
            (profile.shareLinkedIn && profile.linkedInHandle.isNotBlank()) ||
            (profile.shareWebsite && profile.personalWebsite.isNotBlank()) ||
            profile.fullName.isNotBlank()
    return hasAnyData
}