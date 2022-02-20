package com.example.youtubeui

import android.app.Application
import android.util.Log

class YouTubeApplication : Application() {
    private val TAG: String = YouTubeApplication::class.java.simpleName.toString()
    override fun onCreate() {
        super.onCreate()
        Log.d(TAG, "onCreate: ")
    }
}

