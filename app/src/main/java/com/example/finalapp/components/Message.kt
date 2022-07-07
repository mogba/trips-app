package com.example.finalapp.components

import android.content.Context
import android.widget.Toast

fun Message(
    context: Context,
    text: String,
) {
    Toast.makeText(context, text, Toast.LENGTH_LONG).show()
}