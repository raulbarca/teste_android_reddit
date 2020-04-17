package com.fastnews.extensions

import android.os.Build
import android.text.Html

fun String.decodeHtml(): String =
    if (Build.VERSION.SDK_INT >= 24)
        Html.fromHtml(this, Html.FROM_HTML_MODE_LEGACY).toString()
    else
        Html.fromHtml(this).toString()