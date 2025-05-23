package com.aubynsamuel.cardit.domain.usecase

import com.aubynsamuel.cardit.data.repository.ContactRepository
import javax.inject.Inject

class ClearAllContactsUseCase @Inject constructor(
    private val repository: ContactRepository,
) {
    suspend operator fun invoke() {
        return repository.clearAll()
    }
}
