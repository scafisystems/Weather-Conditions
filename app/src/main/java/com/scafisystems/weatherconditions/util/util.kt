package com.scafisystems.weatherconditions.util

import java.text.SimpleDateFormat
import java.util.*

/**
 * Copyright (c) Created by André Scafi on 27/3/2021.
 */

fun String.toDate(
    dateFormat: String = "yyyy-MM-dd HH:mm:ss",
    timeZone: TimeZone = TimeZone.getTimeZone("UTC")
): Date {
    val parser = SimpleDateFormat(dateFormat, Locale.getDefault())
    parser.timeZone = timeZone
    return parser.parse(this)
}

fun Date.formatTo(dateFormat: String, timeZone: TimeZone = TimeZone.getDefault()): String {
    val formatter = SimpleDateFormat(dateFormat, Locale.getDefault())
    formatter.timeZone = timeZone
    return formatter.format(this)
}

fun String.toCelsus(): String {
    return this + " " + 176.toChar() + "C"
}