package com.vitoksmile.reminder.utils

import com.vitoksmile.reminder.R
import com.vitoksmile.reminder.ReminderApplication.Companion.appContext
import java.text.SimpleDateFormat
import java.util.*

fun Date.formatNoteDate(): String {
    val pattern = appContext().getString(R.string.note_date_pattern)
    return SimpleDateFormat(pattern, Locale.getDefault())
        .format(this)
}