package com.aubynsamuel.cardit.domain.usecase

import com.aubynsamuel.cardit.domain.model.UserProfile
import javax.inject.Inject

class ToggleShareDetailUseCase @Inject constructor() {
    operator fun invoke(profile: UserProfile, detail: String, enabled: Boolean): UserProfile {
        return when (detail) {
            "phoneNumber" -> profile.copy(sharePhoneNumber = enabled)
            "emailAddress" -> profile.copy(shareEmail = enabled)
            "instagram" -> profile.copy(shareInstagram = enabled)
            "twitter" -> profile.copy(shareTwitter = enabled)
            "linkedIn" -> profile.copy(shareLinkedIn = enabled)
            "website" -> profile.copy(shareWebsite = enabled)
            "profilePhoto" -> profile.copy(shareProfilePhoto = enabled)
            else -> profile
        }
    }
}
