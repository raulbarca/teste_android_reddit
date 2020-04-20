package com.fastnews

import android.app.Application
import com.fastnews.di.AppComponent.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class RedditApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@RedditApp)
            modules(appModule)
        }
    }
}