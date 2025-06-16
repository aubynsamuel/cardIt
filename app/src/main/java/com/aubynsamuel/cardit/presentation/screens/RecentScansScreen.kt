package com.aubynsamuel.cardit.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.aubynsamuel.cardit.domain.model.UserProfile
import com.aubynsamuel.cardit.presentation.components.ContactItem
import com.aubynsamuel.cardit.presentation.viewmodels.RecentScansViewModel
import com.google.gson.Gson

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecentScansScreen(
    recentScansViewModel: RecentScansViewModel = viewModel(),
    onNavigateToScanDetail: (scannedDataJson: String) -> Unit,
) {
    val recentScans by recentScansViewModel.recentScans.collectAsStateWithLifecycle()
    var showClearConfirmDialog by remember { mutableStateOf(false) }

    if (showClearConfirmDialog) {
        AlertDialog(
            onDismissRequest = { showClearConfirmDialog = false },
            title = { Text("Clear All Scans?") },
            text = { Text("Are you sure you want to delete all recent scans? This action cannot be undone.") },
            confirmButton = {
                TextButton(onClick = {
                    recentScansViewModel.clearAllScans()
                    showClearConfirmDialog = false
                }) { Text("Clear All") }
            },
            dismissButton = {
                TextButton(onClick = { showClearConfirmDialog = false }) { Text("Cancel") }
            }
        )
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Recent Scans") },
                actions = {
                    if (recentScans.isNotEmpty()) {
                        IconButton(onClick = { showClearConfirmDialog = true }) {
                            Text(
                                "Clear",
                                fontWeight = FontWeight.SemiBold,
                                color = MaterialTheme.colorScheme.primary
                            )
                        }
                    }
                }
            )
        }
    ) { paddingValues ->
        if (recentScans.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                contentAlignment = Alignment.Center
            ) {
                Text("No scans yet.")
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize(),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(15.dp)
            ) {
                items(recentScans, key = { it.id }) { scan ->
                    ContactItem(
                        contact = scan,
                        onDelete = { recentScansViewModel.deleteScan(scan) },
                        onClick = {
                            val userProfileFromScan = UserProfile(
                                fullName = scan.fullName,
                                phoneNumber = scan.phoneNumber ?: "",
                                emailAddress = scan.emailAddress ?: "",
                                instagramHandle = scan.instagramHandle ?: "",
                                twitterHandle = scan.twitterHandle ?: "",
                                linkedInHandle = scan.linkedInHandle ?: "",
                                personalWebsite = scan.personalWebsite.toString(),
                                profilePhotoUri = scan.profilePhotoUri.toString(),
                                // Assume all details from a saved scan are meant to be "shared" for display
                                sharePhoneNumber = scan.phoneNumber != null,
                                shareEmail = scan.emailAddress != null,
                                shareInstagram = scan.instagramHandle != null,
                                shareTwitter = scan.twitterHandle != null,
                                shareLinkedIn = scan.linkedInHandle != null,
                                shareWebsite = scan.personalWebsite != null,
                                shareProfilePhoto = scan.profilePhotoUri != null
                            )
                            val scanJson = Gson().toJson(userProfileFromScan)
                            onNavigateToScanDetail(scanJson)
                        }
                    )
                }
            }
        }
    }
}
