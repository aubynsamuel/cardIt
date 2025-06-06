package com.aubynsamuel.cardit.presentation.screens

import android.content.Context
import android.widget.Toast
import androidx.activity.compose.BackHandler
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.net.toUri
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import com.aubynsamuel.cardit.MainActivity
import com.aubynsamuel.cardit.R
import com.aubynsamuel.cardit.presentation.components.DetailItem
import com.aubynsamuel.cardit.presentation.components.ItemRow
import com.aubynsamuel.cardit.presentation.components.SwitchableTextField
import com.aubynsamuel.cardit.presentation.navigation.AppRoutes
import com.aubynsamuel.cardit.presentation.viewmodels.ProfileViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    profileViewModel: ProfileViewModel,
    navController: NavHostController,
) {
    val userProfile = profileViewModel.userProfile
    val context: Context = LocalContext.current
    var backButtonPressed by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()

    BackHandler(enabled = true, onBack = {
        if (backButtonPressed) {
            (context as? MainActivity)?.finish()
        } else {
            backButtonPressed = true
            Toast.makeText(context, "Press again to exit", Toast.LENGTH_SHORT).show()
            scope.launch {
                delay(2000)
                backButtonPressed = false
            }
        }
    })

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
                    error = rememberAsyncImagePainter(R.drawable.person)
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

