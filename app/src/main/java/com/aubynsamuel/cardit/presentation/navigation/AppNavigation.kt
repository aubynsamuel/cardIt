package com.aubynsamuel.cardit.presentation.navigation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.aubynsamuel.cardit.presentation.components.BottomTabs
import com.aubynsamuel.cardit.presentation.screens.ContactScreen
import com.aubynsamuel.cardit.presentation.screens.EditProfileScreen
import com.aubynsamuel.cardit.presentation.screens.MyQrCodeScreen
import com.aubynsamuel.cardit.presentation.screens.ProfileScreen
import com.aubynsamuel.cardit.presentation.screens.RecentScansScreen
import com.aubynsamuel.cardit.presentation.screens.ScanQrScreen
import com.aubynsamuel.cardit.presentation.viewmodels.ProfileViewModel
import com.aubynsamuel.cardit.presentation.viewmodels.RecentScansViewModel
import java.net.URLDecoder

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val profileViewModel: ProfileViewModel = hiltViewModel()
    val recentScansViewModel: RecentScansViewModel = hiltViewModel()

    Scaffold(
        bottomBar = {
            val bottomBarVisible =
                navController.currentBackStackEntryAsState().value?.destination?.route in listOf(
                    AppRoutes.PROFILE,
                    AppRoutes.MY_QR_CODE,
                    AppRoutes.SCAN_QR,
                    AppRoutes.RECENT_SCANS
                )
            AnimatedVisibility(bottomBarVisible) {
                BottomTabs(navController)
            }
        }) { padding ->
        NavHost(
            navController = navController,
            startDestination = AppRoutes.PROFILE,
            modifier = Modifier
                .padding(PaddingValues(bottom = padding.calculateBottomPadding()))
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
                    profileViewModel = profileViewModel, navController = navController
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
                    navController = navController
                )
            }
            composable(AppRoutes.RECENT_SCANS) {
                RecentScansScreen(
                    recentScansViewModel = recentScansViewModel,
                    onNavigateToScanDetail = { data ->
                        navController.navigate(AppRoutes.contactDetailRoute(data))
                    },
                    navController = navController
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
}

