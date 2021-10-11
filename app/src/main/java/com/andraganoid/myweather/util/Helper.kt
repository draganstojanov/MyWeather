package com.andraganoid.myweather.util

import android.view.View
import android.widget.TextView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.andraganoid.myweather.R
import com.google.android.material.snackbar.Snackbar


fun hideKeyboard(view: View) {
    val insetController = ViewCompat.getWindowInsetsController(view)
    insetController?.hide(WindowInsetsCompat.Type.ime())
}

fun View.actionSnackbar(msg: Any?, action: (View) -> Unit) {
    Snackbar.make(this, msg.toString(), Snackbar.LENGTH_INDEFINITE).setAction("OK") { action(this) }.allowInfiniteLines().show()
}

fun View.longSnackbar(msg: Any?) {
    Snackbar.make(this, msg.toString(), Snackbar.LENGTH_LONG).allowInfiniteLines().show()
}

fun View.showSnackbar(msg: Any?) {
    Snackbar.make(this, msg.toString(), Snackbar.LENGTH_SHORT).allowInfiniteLines().show()
}

fun Snackbar.allowInfiniteLines(): Snackbar {
    return apply { (view.findViewById<View?>(R.id.snackbar_text) as? TextView?)?.isSingleLine = false }
}


