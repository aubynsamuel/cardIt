package com.aubynsamuel.cardit.ui.navigation

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.aubynsamuel.cardit.ui.screens.MyQrCodeScreen
import com.aubynsamuel.cardit.ui.screens.ProfileScreen
import com.aubynsamuel.cardit.ui.screens.RecentScansScreen
import com.aubynsamuel.cardit.ui.screens.ScanQrScreen
import com.aubynsamuel.cardit.ui.screens.ScannedContactScreen
import com.aubynsamuel.cardit.viewmodels.ProfileViewModel
import com.aubynsamuel.cardit.viewmodels.RecentScansViewModel
import java.net.URLDecoder

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val profileViewModel: ProfileViewModel = viewModel()
    val recentScansViewModel: RecentScansViewModel = viewModel()

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    val items = listOf(
        ScreenNavItem.Profile,
        ScreenNavItem.MyQRCode,
        ScreenNavItem.ScanQR,
        ScreenNavItem.RecentScans
    )

    Column {
        NavHost(
            navController = navController,
            startDestination = AppRoutes.PROFILE,
            modifier = Modifier.weight(1f)
        ) {
            composable(AppRoutes.PROFILE) {
                ProfileScreen(
                    profileViewModel = profileViewModel,
                    onSaveProfile = {
                        navController.navigate(AppRoutes.MY_QR_CODE)
                    }
                )
            }
            composable(AppRoutes.MY_QR_CODE) {
                MyQrCodeScreen(profileViewModel = profileViewModel)
            }
            composable(AppRoutes.SCAN_QR) {
                ScanQrScreen(
                    recentScansViewModel = recentScansViewModel,
                    onQrCodeScanned = { data ->
                        navController.navigate(AppRoutes.scannedContactDetailRoute(data)) {
                            popUpTo(AppRoutes.SCAN_QR) { inclusive = true }
                        }
                    }
                )
            }
            composable(AppRoutes.RECENT_SCANS) {
                RecentScansScreen(
                    recentScansViewModel = recentScansViewModel,
                    onNavigateToScanDetail = { data ->
                        navController.navigate(AppRoutes.scannedContactDetailRoute(data))
                    }
                )
            }
            composable(
                route = AppRoutes.SCANNED_CONTACT_DETAIL,
                arguments = listOf(navArgument("scannedData") { type = NavType.StringType })
            ) { backStackEntry ->
                val scannedDataEncoded = backStackEntry.arguments?.getString("scannedData") ?: ""
                val scannedDataDecoded = URLDecoder.decode(scannedDataEncoded, "UTF-8")
                ScannedContactScreen(
                    scannedData = scannedDataDecoded,
                    onClose = { navController.popBackStack() } // Or navigate to RecentScans
                )
            }
        }
        NavigationBar(
            modifier = Modifier
        ) {
            items.forEach { screen ->
                NavigationBarItem(
                    icon = { Icon(screen.icon, contentDescription = screen.title) },
                    label = { Text(screen.title) },
                    selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                    onClick = {
                        navController.navigate(screen.route) {
                            popUpTo(AppRoutes.PROFILE) {
                                inclusive = false
                            }
                        }
                    }
                )
            }
        }

    }
}