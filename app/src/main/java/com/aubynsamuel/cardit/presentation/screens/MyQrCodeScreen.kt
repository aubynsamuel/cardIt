package com.aubynsamuel.cardit.presentation.screens

import android.graphics.Bitmap
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
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
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.aubynsamuel.cardit.presentation.utils.QrCodeGenerator
import com.aubynsamuel.cardit.presentation.utils.showToast
import com.aubynsamuel.cardit.presentation.viewmodels.ProfileViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyQrCodeScreen(
    profileViewModel: ProfileViewModel,
) {
    val qrDataString = profileViewModel.qrDataString
    var qrBitmap by remember { mutableStateOf<Bitmap?>(null) }
    var isLoading by remember { mutableStateOf(false) }
    val context = LocalContext.current

    LaunchedEffect(qrDataString) {
        if (qrDataString.isNotBlank()) {
            isLoading = true
            val bitmap = withContext(Dispatchers.Default) {
                QrCodeGenerator.generate(qrDataString)
            }
            qrBitmap = bitmap
            isLoading = false
            if (bitmap == null) {
                showToast("QR Code generation failed", context)
            }
        } else {
            qrBitmap = null
        }
    }
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("My QR Code") }
            )
        },
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            if (qrBitmap != null) {
                Image(
                    bitmap = qrBitmap!!.asImageBitmap(),
                    contentDescription = "My Contact QR Code",
                    modifier = Modifier.size(300.dp)
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    "Scan to get my contact details.",
                    color = MaterialTheme.colorScheme.primary
                )
            } else if (isLoading) {
                CircularProgressIndicator()
                Spacer(modifier = Modifier.height(16.dp))
                Text("Generating QR code...")
            } else {
                Text(
                    "No QR code to display. Check your profile.",
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }
    }
}