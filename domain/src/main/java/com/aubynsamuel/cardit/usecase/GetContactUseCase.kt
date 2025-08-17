package com.aubynsamuel.cardit.usecase

import com.aubynsamuel.cardit.repository.ContactRepository
import com.aubynsamuel.core.model.Contact
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetContactsUseCase @Inject constructor(
    private val repository: ContactRepository,
) {
    operator fun invoke(): Flow<List<Contact>> {
        return repository.getAllContacts()
    }
}
