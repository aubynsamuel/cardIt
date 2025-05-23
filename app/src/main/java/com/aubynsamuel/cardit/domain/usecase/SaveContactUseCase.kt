package com.aubynsamuel.cardit.domain.usecase

import com.aubynsamuel.cardit.data.repository.ContactRepository
import javax.inject.Inject

class SaveContactUseCase @Inject constructor(
    private val repository: ContactRepository,
) {
    suspend operator fun invoke(qrData: String): Boolean {
        return repository.saveContact(qrData)
    }
}
