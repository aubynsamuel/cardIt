package com.aubynsamuel.cardit.usecase

import com.aubynsamuel.cardit.repository.ContactRepository
import javax.inject.Inject

class ClearAllContactsUseCase @Inject constructor(
    private val repository: ContactRepository,
) {
    suspend operator fun invoke() {
        return repository.clearAll()
    }
}
