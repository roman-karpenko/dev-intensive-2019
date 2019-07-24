package ru.skillbranch.devintensive.extensions

import android.app.Activity
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.graphics.Rect
import android.util.TypedValue

fun Activity.hideKeyboard() {
    (this.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager).hideSoftInputFromWindow(this.currentFocus.windowToken, 0)
}

fun Activity.isKeyboardOpen(): Boolean {
    val permissibleError = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 50F, resources.displayMetrics).toInt()
    val rootView = findViewById<View>(android.R.id.content)
    var rect = Rect()
    rootView.getWindowVisibleDisplayFrame(rect)
    val difference = rootView.height - rect.height()
    return (difference > permissibleError)
}

fun Activity.isKeyboardClosed() = !isKeyboardOpen()