package com.aubynsamuel.cardit.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.net.toUri
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import com.aubynsamuel.cardit.ui.components.BottomTabs
import com.aubynsamuel.cardit.ui.components.DetailItem
import com.aubynsamuel.cardit.ui.components.ItemRow
import com.aubynsamuel.cardit.ui.components.SwitchableTextField
import com.aubynsamuel.cardit.ui.navigation.AppRoutes
import com.aubynsamuel.cardit.viewmodels.ProfileViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    profileViewModel: ProfileViewModel = viewModel(),
    navController: NavHostController,
) {
    val userProfile = profileViewModel.userProfile

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Profile") },
                actions = {
                    IconButton(onClick = {
                        navController.navigate(AppRoutes.EDIT_PROFILE) {
                            popUpTo(AppRoutes.PROFILE) {
                                inclusive = false
                            }
                        }
                    }) {
                        Text(
                            text = "Edit",
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 18.sp,
                            modifier = Modifier.padding(end = 5.dp),
                            color = MaterialTheme.colorScheme.primary
                        )
                    }
                },
            )
        },
        bottomBar = { BottomTabs(navController) }
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
                    .clip(CircleShape),
                contentAlignment = Alignment.Center
            ) {
                AsyncImage(
                    model = userProfile.profilePhotoUri.toUri(),
                    contentDescription = "Profile Photo",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop,
                    error = rememberAsyncImagePainter(com.aubynsamuel.cardit.R.drawable.person)
                )
            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(15.dp)
            ) {
                SwitchableTextField(
                    label = "Share Profile Photo",
                    checked = userProfile.shareProfilePhoto,
                    onCheckedChange = { profileViewModel.toggleShareDetail("profilePhoto", it) },
                )

                ItemRow(
                    detailInputField = {
                        DetailItem(
                            profileDetail = userProfile.fullName,
                            label = "Name"
                        )
                    },
                    switch = {}
                )

                ItemRow(
                    detailInputField = {
                        DetailItem(
                            profileDetail = userProfile.phoneNumber,
                            label = "Phone Number"
                        )
                    },
                    switch = {
                        SwitchableTextField(
                            checked = userProfile.sharePhoneNumber,
                            onCheckedChange = {
                                profileViewModel.toggleShareDetail(
                                    "phoneNumber",
                                    it
                                )
                            }
                        )
                    }
                )

                ItemRow(
                    detailInputField = {
                        DetailItem(
                            profileDetail = userProfile.emailAddress,
                            label = "Email Address"
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
                        DetailItem(
                            profileDetail = userProfile.instagramHandle,
                            label = "Instagram (username)"
                        )
                    },
                    switch = {
                        SwitchableTextField(
                            checked = userProfile.shareInstagram,
                            onCheckedChange = {
                                profileViewModel.toggleShareDetail(
                                    "instagram",
                                    it
                                )
                            })
                    }
                )

                ItemRow(
                    detailInputField = {
                        DetailItem(
                            profileDetail = userProfile.twitterHandle,
                            label = "Twitter (username)"
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
                        DetailItem(
                            profileDetail = userProfile.linkedInHandle,
                            label = "LinkedIn (username)"
                        )
                    },
                    switch = {
                        SwitchableTextField(
                            checked = userProfile.shareLinkedIn,
                            onCheckedChange = {
                                profileViewModel.toggleShareDetail(
                                    "linkedIn",
                                    it
                                )
                            })
                    }
                )

                ItemRow(
                    detailInputField = {
                        DetailItem(
                            profileDetail = userProfile.personalWebsite,
                            label = "Personal Website"
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
}

