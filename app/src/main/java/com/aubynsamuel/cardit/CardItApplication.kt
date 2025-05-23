package com.aubynsamuel.cardit

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class CardItApplication : Application() {
    override fun onCreate() {
        super.onCreate()
    }
}