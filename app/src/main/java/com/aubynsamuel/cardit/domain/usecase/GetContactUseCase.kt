package com.aubynsamuel.cardit.domain.usecase

import com.aubynsamuel.cardit.data.repository.ContactRepository
import com.aubynsamuel.cardit.domain.model.Contact
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetContactsUseCase @Inject constructor(
    private val repository: ContactRepository,
) {
    operator fun invoke(): Flow<List<Contact>> {
        return repository.getAllContacts()
    }
}
