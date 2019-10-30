package com.vitoksmile.reminder.extensions

import androidx.annotation.StringRes
import com.google.android.material.textfield.TextInputLayout

fun TextInputLayout.bindError(@StringRes errorResId: Int?) {
    error = errorResId?.let(resources::getString)
}