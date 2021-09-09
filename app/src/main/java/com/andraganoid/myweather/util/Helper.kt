package com.andraganoid.myweather.util

import android.app.Activity
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.andraganoid.myweather.R
import com.google.android.material.snackbar.Snackbar


fun Fragment.hideKeyboard(view: View) {
    val imm: InputMethodManager = requireContext().getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(view.windowToken, 0)
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


