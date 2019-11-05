package com.vitoksmile.reminder.extensions

import android.view.MotionEvent
import android.widget.TextView
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat

fun TextView.setDrawableEnd(@DrawableRes resId: Int) {
    val drawable = ContextCompat.getDrawable(context, resId)
    setCompoundDrawablesWithIntrinsicBounds(null, null, drawable, null)
}

fun TextView.clearDrawableEnd() {
    setCompoundDrawables(null, null, null, null)
}

inline fun TextView.onDrawableEndClicked(crossinline listener: () -> Unit) {
    setOnTouchListener { _, event ->
        if (event.action == MotionEvent.ACTION_UP) {
            val drawable = compoundDrawables.getOrNull(2)
            if (drawable != null) {
                if (event.rawX >= right - drawable.bounds.width()) {
                    listener()
                    return@setOnTouchListener true
                }
            }
        }
        false
    }
}