package com.aubynsamuel.cardit.domain.usecase

import com.aubynsamuel.cardit.data.repository.ProfileRepository
import com.aubynsamuel.cardit.domain.model.UserProfile
import javax.inject.Inject

class SaveProfileUseCase @Inject constructor(
    private val repository: ProfileRepository,
) {
    operator fun invoke(userProfile: UserProfile) {
        repository.saveProfile(userProfile)
    }
}
