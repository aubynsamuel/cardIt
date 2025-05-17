package com.aubynsamuel.cardit.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.QrCode
import androidx.compose.material.icons.filled.QrCodeScanner
import androidx.compose.ui.graphics.vector.ImageVector

sealed class ScreenNavItem(
    val route: String,
    val title: String,
    val icon: ImageVector,
) {
    object Profile : ScreenNavItem(AppRoutes.PROFILE, "Profile", Icons.Filled.AccountCircle)
    object MyQRCode : ScreenNavItem(AppRoutes.MY_QR_CODE, "My QR", Icons.Filled.QrCode)
    object ScanQR : ScreenNavItem(AppRoutes.SCAN_QR, "Scan", Icons.Filled.QrCodeScanner)
    object RecentScans : ScreenNavItem(AppRoutes.RECENT_SCANS, "History", Icons.Filled.History)
}