package com.talkingmedicines.android.extensions

import java.text.SimpleDateFormat
import java.util.*

fun Date.getUTCTimeString(): String {
    val simpleDateFormat = SimpleDateFormat("dd/MMM/yyyy HH:mm")
    simpleDateFormat.timeZone = TimeZone.getTimeZone("UTC")
    return simpleDateFormat.format(this)
}

fun Date.getLocalTimeString(): String {
    val simpleDateFormat = SimpleDateFormat("dd/MMM/yyyy HH:mm")
    return simpleDateFormat.format(this)
}