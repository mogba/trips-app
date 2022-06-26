package com.example.core.finalapp.extensions

import java.text.SimpleDateFormat
import java.util.*

fun Date?.toStringFormat(format: String): String {
    if (this === null) return ""
    val dateFormatter = SimpleDateFormat(format, Locale.getDefault())
    return dateFormatter.format(this)
}