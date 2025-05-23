package com.aubynsamuel.cardit.domain.usecase

import com.aubynsamuel.cardit.data.repository.ContactRepository
import com.aubynsamuel.cardit.domain.model.Contact
import javax.inject.Inject

class DeleteContactUseCase @Inject constructor(
    private val repository: ContactRepository,
) {
    suspend operator fun invoke(contact: Contact) {
        return repository.deleteContact(contact)
    }
}
