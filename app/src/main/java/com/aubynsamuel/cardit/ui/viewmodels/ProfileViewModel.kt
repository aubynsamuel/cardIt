package com.aubynsamuel.cardit.ui.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aubynsamuel.cardit.usecase.GenerateQrDataUseCase
import com.aubynsamuel.cardit.usecase.GetProfileUseCase
import com.aubynsamuel.cardit.usecase.SaveProfileUseCase
import com.aubynsamuel.cardit.usecase.ToggleShareDetailUseCase
import com.aubynsamuel.cardit.usecase.UpdateProfileDetailUseCase
import com.aubynsamuel.core.model.ProfileEnum
import com.aubynsamuel.core.model.UserProfile
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val getProfileUseCase: GetProfileUseCase,
    private val saveProfileUseCase: SaveProfileUseCase,
    private val updateProfileDetailUseCase: UpdateProfileDetailUseCase,
    private val toggleShareDetailUseCase: ToggleShareDetailUseCase,
    private val generateQrDataUseCase: GenerateQrDataUseCase,
) : ViewModel() {

    var userProfile by mutableStateOf(UserProfile())
        private set

    var qrDataString by mutableStateOf("")
        private set

    init {
        loadProfile()
    }

    private fun loadProfile() {
        viewModelScope.launch {
            userProfile = getProfileUseCase()
            updateQrData()
        }
    }

    fun updateProfileDetails(detail: ProfileEnum, value: String) {
        userProfile = updateProfileDetailUseCase(userProfile, detail, value)
        updateQrData()
    }

    fun toggleShareDetail(detail: String, enabled: Boolean) {
        userProfile = toggleShareDetailUseCase(userProfile, detail, enabled)
        updateQrData()
        saveProfile()
    }

    fun saveProfile() {
        viewModelScope.launch {
            saveProfileUseCase(userProfile)
        }
    }

    private fun updateQrData() {
        qrDataString = generateQrDataUseCase(userProfile)
    }
}
