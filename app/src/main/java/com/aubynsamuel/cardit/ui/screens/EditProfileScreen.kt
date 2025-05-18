package com.aubynsamuel.cardit.ui.screens

import android.content.Intent
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.aubynsamuel.cardit.models.ProfileEnum
import com.aubynsamuel.cardit.ui.components.ItemRow
import com.aubynsamuel.cardit.ui.components.SwitchableTextField
import com.aubynsamuel.cardit.viewmodels.ProfileViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditProfileScreen(
    profileViewModel: ProfileViewModel = viewModel(),
    onSaveProfile: () -> Unit,
) {
    val userProfile = profileViewModel.userProfile
    val context = LocalContext.current

    val singlePhotoPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = { uri ->
            uri?.let {
                val contentResolver = context.contentResolver
                try {
                    contentResolver.takePersistableUriPermission(
                        it,
                        Intent.FLAG_GRANT_READ_URI_PERMISSION
                    )
                } catch (e: SecurityException) {
                    e.printStackTrace()
                }
                profileViewModel.updateProfileDetails(ProfileEnum.PROFILE_URI, it.toString())
            }
        }
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Edit Profile") },
                actions = {
                    IconButton(onClick = {
                        profileViewModel.saveProfile()
                        onSaveProfile()
                    }) {
                        Icon(
                            Icons.Filled.Done,
                            contentDescription = "Save Profile",
                            modifier = Modifier.size(30.dp),
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                },
            )
        },
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(horizontal = 16.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(120.dp)
                    .clip(CircleShape)
                    .clickable {
                        singlePhotoPickerLauncher.launch(
                            PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                        )
                    },
                contentAlignment = Alignment.Center
            ) {
                if (userProfile.profilePhotoUri.isNotBlank()) {
                    AsyncImage(
                        model = userProfile.profilePhotoUri.toUri(),
                        contentDescription = "Profile Photo",
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )
                } else {
                    Icon(
                        Icons.Default.Person,
                        contentDescription = "Profile Picture Placeholder",
                        modifier = Modifier
                            .fillMaxSize()
                            .border(width = 1.dp, shape = CircleShape, color = Color.Gray),
                    )
                }
            }
            Text(
                text = "Change Profile Picture",
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.clickable {
                    singlePhotoPickerLauncher.launch(
                        PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                    )
                })

            ItemRow(
                detailInputField = {
                    OutlinedTextField(
                        value = userProfile.fullName,
                        onValueChange = {
                            profileViewModel.updateProfileDetails(
                                ProfileEnum.FULL_NAME,
                                it
                            )
                        },
                        label = { Text("Name") },
                        modifier = Modifier.fillMaxWidth()
                    )
                },
                switch = {}
            )

            ItemRow(
                detailInputField = {
                    OutlinedTextField(
                        value = userProfile.phoneNumber,
                        onValueChange = {
                            profileViewModel.updateProfileDetails(
                                ProfileEnum.PHONE_NUMBER,
                                it
                            )
                        },
                        label = { Text("Phone Number") },
                        modifier = Modifier.fillMaxWidth()
                    )
                },
                switch = {
                    SwitchableTextField(
                        checked = userProfile.sharePhoneNumber,
                        onCheckedChange = { profileViewModel.toggleShareDetail("phoneNumber", it) }
                    )
                }
            )

            ItemRow(
                detailInputField = {
                    OutlinedTextField(
                        value = userProfile.emailAddress,
                        onValueChange = {
                            profileViewModel.updateProfileDetails(
                                ProfileEnum.EMAIL_ADDRESS,
                                it
                            )
                        },
                        label = { Text("Email Address") },
                        modifier = Modifier.fillMaxWidth()
                    )
                },
                switch = {
                    SwitchableTextField(
                        checked = userProfile.shareEmail,
                        onCheckedChange = {
                            profileViewModel.toggleShareDetail(
                                "emailAddress",
                                it
                            )
                        })
                }
            )

            ItemRow(
                detailInputField = {
                    OutlinedTextField(
                        value = userProfile.instagramHandle,
                        onValueChange = {
                            profileViewModel.updateProfileDetails(
                                ProfileEnum.INSTAGRAM_HANDLE,
                                it
                            )
                        },
                        label = { Text("Instagram (username)") },
                        modifier = Modifier.fillMaxWidth()
                    )
                },
                switch = {
                    SwitchableTextField(
                        checked = userProfile.shareInstagram,
                        onCheckedChange = { profileViewModel.toggleShareDetail("instagram", it) })
                }
            )

            ItemRow(
                detailInputField = {
                    OutlinedTextField(
                        value = userProfile.twitterHandle,
                        onValueChange = {
                            profileViewModel.updateProfileDetails(
                                ProfileEnum.TWITTER_HANDLE,
                                it
                            )
                        },
                        label = { Text("Twitter (username)") },
                        modifier = Modifier.fillMaxWidth()
                    )
                },
                switch = {
                    SwitchableTextField(
                        checked = userProfile.shareTwitter,
                        onCheckedChange = { profileViewModel.toggleShareDetail("twitter", it) })
                }
            )

            ItemRow(
                detailInputField = {
                    OutlinedTextField(
                        value = userProfile.linkedInHandle,
                        onValueChange = {
                            profileViewModel.updateProfileDetails(
                                ProfileEnum.LINKEDIN_HANDLE,
                                it
                            )
                        },
                        label = { Text("LinkedIn (username)") },
                        modifier = Modifier.fillMaxWidth()
                    )
                },
                switch = {
                    SwitchableTextField(
                        checked = userProfile.shareLinkedIn,
                        onCheckedChange = { profileViewModel.toggleShareDetail("linkedIn", it) })
                }
            )

            ItemRow(
                detailInputField = {
                    OutlinedTextField(
                        value = userProfile.personalWebsite,
                        onValueChange = {
                            profileViewModel.updateProfileDetails(
                                ProfileEnum.PERSONAL_WEBSITE,
                                it
                            )
                        },
                        label = { Text("Personal Website (Optional)") },
                        modifier = Modifier.fillMaxWidth()
                    )
                },
                switch = {
                    SwitchableTextField(
                        checked = userProfile.shareWebsite,
                        onCheckedChange = { profileViewModel.toggleShareDetail("website", it) })
                }
            )
        }
    }
}