package com.example.pokeapp.ui

import android.app.Application
import com.example.pokeapp.data.AppContainer
import com.example.pokeapp.data.DefaultAppContainer

class MarsPhotosApplication : Application() {
    /** AppContainer instance used by the rest of classes to obtain dependencies */
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }
}