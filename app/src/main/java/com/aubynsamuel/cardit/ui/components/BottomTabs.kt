package com.aubynsamuel.cardit.ui.components

import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState
import com.aubynsamuel.cardit.ui.navigation.AppRoutes
import com.aubynsamuel.cardit.ui.navigation.navItems

@Composable
fun BottomTabs(navController: NavController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    NavigationBar(containerColor = MaterialTheme.colorScheme.primaryContainer) {
        navItems.forEach { screen ->
            NavigationBarItem(
                colors = NavigationBarItemDefaults.colors()
                    .copy(
                        selectedIndicatorColor = MaterialTheme.colorScheme.onPrimaryContainer,
                        selectedIconColor = MaterialTheme.colorScheme.primaryContainer
                    ),
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
