package com.fastnews.runner

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.loadKoinModules
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.core.module.Module

class RedditTestApp : Application() {
    override fun onCreate() {
        super.onCreate()
        stopKoin()
        startKoin {
            androidLogger()
            androidContext(this@RedditTestApp)
            modules(emptyList())
        }
    }

    internal fun injectModule(module: Module) {
        loadKoinModules(module)
    }
}