package com.vitoksmile.reminder.dialogs

import androidx.fragment.app.Fragment
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog
import java.util.*

class DateTimePickerDialog(
    private val fragment: Fragment,
    selectedDate: Date?,
    private val onDatePicked: (Date) -> Unit
) {

    private val calendar = Calendar.getInstance()

    private val dateListener = DatePickerDialog.OnDateSetListener { _, year, month, day ->
        calendar.apply {
            set(Calendar.DAY_OF_MONTH, day)
            set(Calendar.MONTH, month)
            set(Calendar.YEAR, year)
        }
        pickTime()
    }
    private val timeListener = TimePickerDialog.OnTimeSetListener { _, hour, minute, second ->
        calendar.apply {
            set(Calendar.HOUR_OF_DAY, hour)
            set(Calendar.MINUTE, minute)
            set(Calendar.SECOND, 0)
            set(Calendar.MILLISECOND, 0)
        }
        onPicked()
    }

    private var dateDialog: DatePickerDialog? = null
    private var timeDialog: TimePickerDialog? = null

    init {
        selectedDate?.let(calendar::setTime)
        dateDialog = DatePickerDialog.newInstance(dateListener, calendar).apply {
            dismissOnPause(true)
            show(fragment.requireFragmentManager(), "DatePickerDialog")
        }
    }

    private fun pickTime() {
        val hour = calendar.get(Calendar.HOUR_OF_DAY)
        val minute = calendar.get(Calendar.MINUTE)
        val second = calendar.get(Calendar.SECOND)
        timeDialog = TimePickerDialog.newInstance(timeListener, hour, minute, second, true).apply {
            dismissOnPause(true)
            show(fragment.requireFragmentManager(), "TimePickerDialog")
        }
    }

    private fun onPicked() {
        onDatePicked(calendar.time)
    }
}

fun Fragment.pickDateTime(
    selectedDate: Date?,
    onDatePicked: (Date) -> Unit
) = DateTimePickerDialog(this, selectedDate, onDatePicked)