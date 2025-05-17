package com.aubynsamuel.cardit

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.aubynsamuel.cardit.ui.navigation.AppNavigation
import com.aubynsamuel.cardit.ui.theme.CardItTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CardItTheme {
                AppNavigation()
            }
        }
    }
}