package com.example.poketwordtranslator

import android.app.Application

class App : Application() {
    companion object {
        var instance: Application? = null
            private set
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}