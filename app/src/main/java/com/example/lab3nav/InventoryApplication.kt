package com.example.lab3nav

import android.app.Application
import com.example.lab3nav.data.AppContainer
import com.example.lab3nav.data.DefaultAppContainer

class InventoryApplication : Application() {
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }
}