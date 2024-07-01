package dev.levi

import android.app.Application
import di.InitializeKoin

class Tunnel: Application() {
    override fun onCreate() {
        super.onCreate()

        // Initialize Koin
         InitializeKoin(applicationContext).initKoin()
    }
}