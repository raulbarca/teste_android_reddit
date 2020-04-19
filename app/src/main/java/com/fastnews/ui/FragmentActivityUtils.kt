package com.fastnews.ui

import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity

object FragmentActivityUtils {
    fun getSupportBar(activity: FragmentActivity?): ActionBar? {
        return try {
            (activity as AppCompatActivity).supportActionBar
        } catch (t: Throwable) {
            null
        }
    }
}