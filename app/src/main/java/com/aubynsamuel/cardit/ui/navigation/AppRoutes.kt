package com.aubynsamuel.cardit.ui.navigation

import java.net.URLEncoder

object AppRoutes {
    const val PROFILE = "profile"
    const val MY_QR_CODE = "my_qr_code"
    const val SCAN_QR = "scan_qr"
    const val RECENT_SCANS = "recent_scans"
    const val SCANNED_CONTACT_DETAIL = "scanned_contact_detail/{scannedData}"

    fun scannedContactDetailRoute(scannedData: String): String {
        // URL encode scannedData to handle special characters in route
        val encodedData = URLEncoder.encode(scannedData, "UTF-8")
        return "scanned_contact_detail/$encodedData"
    }
}