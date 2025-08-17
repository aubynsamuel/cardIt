package com.aubynsamuel.cardit.usecase

import com.aubynsamuel.cardit.repository.ContactRepository
import javax.inject.Inject

class SaveContactUseCase @Inject constructor(
    private val repository: ContactRepository,
) {
    suspend operator fun invoke(qrData: String): Boolean {
        return repository.saveContact(qrData)
    }
}
