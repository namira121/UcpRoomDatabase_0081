package com.example.ucpduaa

import android.app.Application
import com.example.ucpduaa.dependenciesinjection.ContainerApp

class KrsApp: Application() {
    lateinit var ContainerApp: ContainerApp

    override fun onCreate() {
        super.onCreate()
        ContainerApp = ContainerApp(this)
    }
}