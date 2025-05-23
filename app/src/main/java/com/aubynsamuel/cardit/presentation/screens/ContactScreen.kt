package com.aubynsamuel.cardit.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Link
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.unit.dp
import com.aubynsamuel.cardit.domain.model.UserProfile
import com.google.gson.Gson

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContactScreen(
    scannedData: String,
    onClose: () -> Unit,
) {
    var displayedProfile by remember { mutableStateOf<UserProfile?>(null) }
    var errorParsing by remember { mutableStateOf(false) }
    val uriHandler = LocalUriHandler.current

    LaunchedEffect(scannedData) {
        try {
            displayedProfile = Gson().fromJson(scannedData, UserProfile::class.java)
            errorParsing = false
        } catch (e: Exception) {
            errorParsing = true
        }
    }
    Scaffold(
        topBar = {
            TopAppBar(title = {
                Text(
                    displayedProfile?.fullName ?: "",
                    style = MaterialTheme.typography.headlineSmall
                )
            })
        },
    ) { paddingValues ->
        Column(
            Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(25.dp)
        ) {
            if (errorParsing || displayedProfile == null) {
                Text("Could not parse contact details. Raw data:")
                Text(scannedData)
            } else {
                val profile = displayedProfile!!
//                Text(profile.fullName, style = MaterialTheme.typography.headlineSmall)

                if (profile.sharePhoneNumber && profile.phoneNumber.isNotBlank()) {
                    RowItems {
                        Icon(Icons.Default.Phone, contentDescription = "")
                        ClickableText(
                            text = AnnotatedString(profile.phoneNumber),
                            style = MaterialTheme.typography.bodyLarge.copy(color = MaterialTheme.colorScheme.primary),
                            onClick = { uriHandler.openUri("tel:${profile.phoneNumber}") }
                        )
                    }
                }
                if (profile.shareEmail && profile.emailAddress.isNotBlank()) {
                    RowItems {
                        Icon(Icons.Default.Email, contentDescription = "")
                        ClickableText(
                            text = AnnotatedString(profile.emailAddress),
                            style = MaterialTheme.typography.bodyLarge.copy(color = MaterialTheme.colorScheme.primary),
                            onClick = { uriHandler.openUri("mailto:${profile.emailAddress}") }
                        )
                    }
                }
                if (profile.shareInstagram && profile.instagramHandle.isNotBlank()) {
                    RowItems {
                        ClickableText(
                            text = AnnotatedString("📸   @${profile.instagramHandle}"),
                            style = MaterialTheme.typography.bodyLarge.copy(color = MaterialTheme.colorScheme.primary),
                            onClick = { uriHandler.openUri("https://instagram.com/${profile.instagramHandle}") }
                        )
                    }
                }
                if (profile.shareTwitter && profile.twitterHandle.isNotBlank()) {
                    RowItems {
                        ClickableText(
                            text = AnnotatedString("🐦   @${profile.twitterHandle}"),
                            style = MaterialTheme.typography.bodyLarge.copy(color = MaterialTheme.colorScheme.primary),
                            onClick = { uriHandler.openUri("https://twitter.com/${profile.twitterHandle}") }
                        )
                    }
                }
                if (profile.shareLinkedIn && profile.linkedInHandle.isNotBlank()) {
                    RowItems {
                        ClickableText(
                            text = AnnotatedString("💼   ${profile.linkedInHandle}"),
                            style = MaterialTheme.typography.bodyLarge.copy(color = MaterialTheme.colorScheme.primary),
                            onClick = { uriHandler.openUri("https://linkedin.com/in/${profile.linkedInHandle}") }
                        )
                    }
                }
                if (profile.shareWebsite && profile.personalWebsite.isNotBlank() == true) {
                    RowItems {
                        Icon(Icons.Default.Link, contentDescription = "")
                        ClickableText(
                            text = AnnotatedString(profile.personalWebsite),
                            style = MaterialTheme.typography.bodyLarge.copy(color = MaterialTheme.colorScheme.primary),
                            onClick = { uriHandler.openUri(profile.personalWebsite) }
                        )
                    }
                }
            }

            Spacer(Modifier.weight(1f))

            Button(
                onClick = onClose,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Text("Close")
            }
        }
    }
}

@Composable
fun RowItems(items: @Composable (() -> Unit)) {
    Row(
        modifier = Modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        items()
    }
}