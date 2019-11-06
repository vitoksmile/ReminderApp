package com.vitoksmile.reminder.scheduler

import java.util.*

class Task(
    val id: Long,
    val time: Date,
    val serviceClassName: String
)

internal class TaskData(
    val id: Long,
    val serviceClassName: String
)