package com.fastnews.rule

import android.content.Intent
import androidx.fragment.app.Fragment
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import com.fastnews.runner.RedditTestApp
import com.fastnews.ui.ParentActivity
import org.koin.core.module.Module

abstract class FragmentTestRule<F : Fragment> :
    ActivityTestRule<ParentActivity>(ParentActivity::class.java, true) {
    override fun afterActivityLaunched() {
        super.afterActivityLaunched()
        activity.runOnUiThread {
            val fm = activity.supportFragmentManager
            val transaction = fm.beginTransaction()
            transaction.replace(
                android.R.id.content,
                createFragment()
            ).commit()
        }
    }

    override fun beforeActivityLaunched() {
        super.beforeActivityLaunched()
        val application = InstrumentationRegistry.getInstrumentation()
            .targetContext.applicationContext as RedditTestApp
        application.injectModule(getModule())
    }

    protected abstract fun createFragment(): F

    protected abstract fun getModule(): Module

    fun launch() {
        launchActivity(Intent())
    }
}

fun <F : Fragment> createRule(fragment: F, module: Module): FragmentTestRule<F> {
    return object : FragmentTestRule<F>() {
        override fun createFragment(): F = fragment
        override fun getModule(): Module = module
    }
}