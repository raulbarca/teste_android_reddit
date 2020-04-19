package com.fastnews.runner

import android.app.Application
import android.content.Context
import androidx.test.runner.AndroidJUnitRunner

class RedditTestRunner : AndroidJUnitRunner() {
    override fun newApplication(
        cl: ClassLoader?,
        className: String?,
        context: Context?
    ): Application {
        return super.newApplication(
            cl, RedditTestApp::class.java.name, context
        )
    }
}