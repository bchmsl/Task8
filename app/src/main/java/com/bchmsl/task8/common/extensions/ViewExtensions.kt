package com.bchmsl.task8.common.extensions

import android.view.View
import com.google.android.material.snackbar.Snackbar

fun View.makeSnackbar(message: String?, isLengthLong: Boolean = false) {
    when {
        isLengthLong -> Snackbar.make(this, "$message", Snackbar.LENGTH_LONG).show()
        else -> Snackbar.make(this, "$message", Snackbar.LENGTH_SHORT).show()
    }
}