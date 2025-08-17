package com.aubynsamuel.cardit.usecase

import com.aubynsamuel.cardit.repository.ProfileRepository
import com.aubynsamuel.core.model.UserProfile
import javax.inject.Inject

class GetProfileUseCase @Inject constructor(
    private val repository: ProfileRepository,
) {
    operator fun invoke(): UserProfile {
        return repository.loadProfile()
    }
}
