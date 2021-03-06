package com.smb.tictactoe

import android.app.Application
import com.smb.components.di.uiModule
import com.smb.tictactoe.di.presentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class BaseApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@BaseApplication)
            modules(listOf(presentationModule, uiModule))
        }
    }
}