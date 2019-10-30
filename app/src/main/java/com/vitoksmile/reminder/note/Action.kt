package com.vitoksmile.reminder.note

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

sealed class Action : Parcelable {
    @Parcelize
    object NewNote : Action()

    @Parcelize
    class UpdateNote(val noteId: Long) : Action()
}