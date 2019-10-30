package com.vitoksmile.reminder.extensions

import android.widget.EditText

var EditText.inputtedText: String
    set(value) {
        setText(value)
    }
    get() = text?.toString() ?: ""