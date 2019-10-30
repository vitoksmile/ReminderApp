package com.vitoksmile.reminder.note

import androidx.annotation.StringRes
import androidx.lifecycle.MutableLiveData
import com.vitoksmile.reminder.R

class NoteValidator(
    private val liveData: MutableLiveData<Status>
) {

    sealed class Status {
        class Title(@StringRes val errorResId: Int?) : Status()
        class Body(@StringRes val errorResId: Int?) : Status()
    }

    private companion object {

        const val BODY_LENGTH_MIN = 1
    }

    @Suppress("UNUSED_PARAMETER")
    fun isValid(title: String, body: String): Boolean {
        if (body.length < BODY_LENGTH_MIN) {
            liveData.value = Status.Body(R.string.error_body_length)
            return false
        } else {
            liveData.value = Status.Body(null)
        }

        return true
    }
}