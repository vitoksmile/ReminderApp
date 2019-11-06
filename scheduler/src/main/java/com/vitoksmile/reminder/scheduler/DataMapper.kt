package com.vitoksmile.reminder.scheduler

import androidx.work.Data

private const val KEY_ID = "KEY_ID"
private const val KEY_SERVICE_CLASS_NAME = "KEY_SERVICE_CLASS_NAME"

internal fun Task.toData() = Data.Builder()
    .putLong(KEY_ID, id)
    .putString(KEY_SERVICE_CLASS_NAME, serviceClassName)
    .build()

internal fun Data.toTask() = TaskData(
    id = getLong(KEY_ID, -1),
    serviceClassName = getString(KEY_SERVICE_CLASS_NAME)!!
)