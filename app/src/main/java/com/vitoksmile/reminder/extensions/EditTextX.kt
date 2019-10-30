package com.vitoksmile.reminder.extensions

import android.widget.EditText

val EditText.inputtedText: String
    get() = text?.toString() ?: ""