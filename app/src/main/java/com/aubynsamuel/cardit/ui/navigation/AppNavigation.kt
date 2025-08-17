package com.aubynsamuel.cardit.ui.navigation

import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.aubynsamuel.cardit.ui.screens.ContactScreen
import com.aubynsamuel.cardit.ui.screens.EditProfileScreen
import com.aubynsamuel.cardit.ui.screens.MyQrCodeScreen
import com.aubynsamuel.cardit.ui.screens.ProfileScreen
import com.aubynsamuel.cardit.ui.screens.RecentScansScreen
import com.aubynsamuel.cardit.ui.screens.ScanQrScreen
import com.aubynsamuel.cardit.ui.viewmodels.ProfileViewModel
import com.aubynsamuel.cardit.ui.viewmodels.RecentScansViewModel
import java.net.URLDecoder

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val profileViewModel: ProfileViewModel = hiltViewModel()
    val recentScansViewModel: RecentScansViewModel = hiltViewModel()

    NavHost(
        navController = navController,
        startDestination = AppRoutes.PROFILE,
    ) {
        composable(AppRoutes.PROFILE) {
            ProfileScreen(
                profileViewModel = profileViewModel,
                navController = navController
            )
        }
        composable(AppRoutes.EDIT_PROFILE) {
            EditProfileScreen(
                profileViewModel = profileViewModel,
                onSaveProfile = {
                    navController.popBackStack()
                },
            )
        }
        composable(AppRoutes.MY_QR_CODE) {
            MyQrCodeScreen(
                profileViewModel = profileViewModel,
                navController = navController,
            )
        }
        composable(AppRoutes.SCAN_QR) {
            ScanQrScreen(
                recentScansViewModel = recentScansViewModel,
                onQrCodeScanned = { data ->
                    navController.navigate(AppRoutes.contactDetailRoute(data)) {
                        popUpTo(AppRoutes.SCAN_QR) {
                            inclusive = false
                        }
                    }
                },
                navController = navController,
            )
        }
        composable(AppRoutes.RECENT_SCANS) {
            RecentScansScreen(
                recentScansViewModel = recentScansViewModel,
                onNavigateToScanDetail = { data ->
                    navController.navigate(AppRoutes.contactDetailRoute(data))
                },
                navController = navController,
            )
        }
        composable(
            route = AppRoutes.CONTACT_DETAIL,
            arguments = listOf(navArgument("scannedData") { type = NavType.StringType })
        ) { backStackEntry ->
            val scannedDataEncoded = backStackEntry.arguments?.getString("scannedData") ?: ""
            val scannedDataDecoded = URLDecoder.decode(scannedDataEncoded, "UTF-8")
            ContactScreen(
                scannedData = scannedDataDecoded,
                onClose = { navController.popBackStack() },
            )
        }
    }
}



