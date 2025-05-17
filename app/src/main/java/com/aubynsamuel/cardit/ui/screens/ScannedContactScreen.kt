package com.aubynsamuel.cardit.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
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
import com.aubynsamuel.cardit.models.UserProfile
import com.google.gson.Gson

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScannedContactScreen(
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
        topBar = { TopAppBar(title = { Text("Scanned Contact") }) }
    ) { paddingValues ->
        Column(
            Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(15.dp)
        ) {
            if (errorParsing || displayedProfile == null) {
                Text("Could not parse contact details. Raw data:")
                Text(scannedData)
            } else {
                val profile = displayedProfile!!
                Text(profile.fullName, style = MaterialTheme.typography.headlineSmall)

                if (profile.sharePhoneNumber && profile.phoneNumber.isNotBlank()) {
                    ClickableText(
                        text = AnnotatedString("📞 ${profile.phoneNumber}"),
                        style = MaterialTheme.typography.bodyLarge.copy(color = MaterialTheme.colorScheme.primary),
                        onClick = { uriHandler.openUri("tel:${profile.phoneNumber}") }
                    )
                }
                if (profile.shareEmail && profile.emailAddress.isNotBlank()) {
                    ClickableText(
                        text = AnnotatedString("✉️ ${profile.emailAddress}"),
                        style = MaterialTheme.typography.bodyLarge.copy(color = MaterialTheme.colorScheme.primary),
                        onClick = { uriHandler.openUri("mailto:${profile.emailAddress}") }
                    )
                }
                if (profile.shareInstagram && profile.instagramHandle.isNotBlank()) {
                    ClickableText(
                        text = AnnotatedString("📸 @${profile.instagramHandle}"),
                        style = MaterialTheme.typography.bodyLarge.copy(color = MaterialTheme.colorScheme.primary),
                        onClick = { uriHandler.openUri("https://instagram.com/${profile.instagramHandle}") }
                    )
                }
                if (profile.shareTwitter && profile.twitterHandle.isNotBlank()) {
                    ClickableText(
                        text = AnnotatedString("🐦 @${profile.twitterHandle}"),
                        style = MaterialTheme.typography.bodyLarge.copy(color = MaterialTheme.colorScheme.primary),
                        onClick = { uriHandler.openUri("https://twitter.com/${profile.twitterHandle}") }
                    )
                }
                if (profile.shareLinkedIn && profile.linkedInHandle.isNotBlank()) {
                    ClickableText(
                        text = AnnotatedString("💼 ${profile.linkedInHandle}"),
                        style = MaterialTheme.typography.bodyLarge.copy(color = MaterialTheme.colorScheme.primary),
                        onClick = { uriHandler.openUri("https://linkedin.com/in/${profile.linkedInHandle}") }
                    )
                }
                if (profile.shareWebsite && profile.personalWebsite?.isNotBlank() == true) {
                    ClickableText(
                        text = AnnotatedString("🔗 ${profile.personalWebsite}"),
                        style = MaterialTheme.typography.bodyLarge.copy(color = MaterialTheme.colorScheme.primary),
                        onClick = { uriHandler.openUri(profile.personalWebsite) }
                    )
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
