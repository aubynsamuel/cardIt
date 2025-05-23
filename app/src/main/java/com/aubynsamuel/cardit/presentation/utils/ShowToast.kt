package com.aubynsamuel.cardit.presentation.utils

import android.content.Context
import android.widget.Toast

fun showToast(msg: String, context: Context) {
    Toast.makeText(context, msg, Toast.LENGTH_LONG).show()
}