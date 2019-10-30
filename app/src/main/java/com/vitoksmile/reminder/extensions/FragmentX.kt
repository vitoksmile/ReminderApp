package com.vitoksmile.reminder.extensions

import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

fun <T> Fragment.observe(liveData: LiveData<T>, action: T.() -> Unit) {
    liveData.observe(viewLifecycleOwner, Observer(action))
}