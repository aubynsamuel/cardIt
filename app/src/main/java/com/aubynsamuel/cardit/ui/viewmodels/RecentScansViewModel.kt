package com.aubynsamuel.cardit.ui.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aubynsamuel.cardit.usecase.ClearAllContactsUseCase
import com.aubynsamuel.cardit.usecase.DeleteContactUseCase
import com.aubynsamuel.cardit.usecase.GetContactsUseCase
import com.aubynsamuel.cardit.usecase.SaveContactUseCase
import com.aubynsamuel.core.model.Contact
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecentScansViewModel @Inject constructor(
    getScannedContactsUseCase: GetContactsUseCase,
    private val saveContactUseCase: SaveContactUseCase,
    private val deleteContactUseCase: DeleteContactUseCase,
    private val clearAllContactsUseCase: ClearAllContactsUseCase,
) : ViewModel() {

    val recentScans: StateFlow<List<Contact>> = getScannedContactsUseCase()
        .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    fun saveContact(qrData: String) {
        viewModelScope.launch {
            try {
                saveContactUseCase(qrData)
            } catch (e: Exception) {
                Log.e("RecentScansViewModel", "Error savings contact${e.message.toString()}")
            }
        }
    }

    fun deleteScan(contact: Contact) {
        viewModelScope.launch {
            deleteContactUseCase(contact)
        }
    }

    fun clearAllScans() {
        viewModelScope.launch {
            clearAllContactsUseCase()
        }
    }
}
