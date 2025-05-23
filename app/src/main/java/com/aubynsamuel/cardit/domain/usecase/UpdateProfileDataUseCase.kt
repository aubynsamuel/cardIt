package com.aubynsamuel.cardit.domain.usecase

import com.aubynsamuel.cardit.domain.model.ProfileEnum
import com.aubynsamuel.cardit.domain.model.UserProfile
import javax.inject.Inject

class UpdateProfileDetailUseCase @Inject constructor() {
    operator fun invoke(profile: UserProfile, detail: ProfileEnum, value: String): UserProfile {
        return when (detail) {
            ProfileEnum.FULL_NAME -> profile.copy(fullName = value)
            ProfileEnum.PHONE_NUMBER -> profile.copy(phoneNumber = value)
            ProfileEnum.EMAIL_ADDRESS -> profile.copy(emailAddress = value)
            ProfileEnum.INSTAGRAM_HANDLE -> profile.copy(instagramHandle = value)
            ProfileEnum.TWITTER_HANDLE -> profile.copy(twitterHandle = value)
            ProfileEnum.LINKEDIN_HANDLE -> profile.copy(linkedInHandle = value)
            ProfileEnum.PERSONAL_WEBSITE -> profile.copy(personalWebsite = value)
            ProfileEnum.PROFILE_URI -> profile.copy(profilePhotoUri = value)
        }
    }
}
