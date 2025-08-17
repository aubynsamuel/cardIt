package com.aubynsamuel.cardit.usecase

import com.aubynsamuel.cardit.repository.ContactRepository
import com.aubynsamuel.core.model.Contact
import javax.inject.Inject

class DeleteContactUseCase @Inject constructor(
    private val repository: ContactRepository,
) {
    suspend operator fun invoke(contact: Contact) {
        return repository.deleteContact(contact)
    }
}
