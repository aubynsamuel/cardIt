package com.aubynsamuel.cardit.presentation.navigation

import java.net.URLEncoder

object AppRoutes {
    const val PROFILE = "profile"
    const val EDIT_PROFILE = "edit_profile"
    const val MY_QR_CODE = "my_qr_code"
    const val SCAN_QR = "scan_qr"
    const val RECENT_SCANS = "recent_scans"
    const val CONTACT_DETAIL = "scanned_contact_detail/{scannedData}"

    fun contactDetailRoute(scannedData: String): String {
        // URL encode scannedData to handle special characters in route
        val encodedData = URLEncoder.encode(scannedData, "UTF-8")
        return "scanned_contact_detail/$encodedData"
    }
}