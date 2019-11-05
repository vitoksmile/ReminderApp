package com.vitoksmile.reminder.domain.models

import java.util.*

data class Note(
    val id: Long,
    val title: String,
    val body: String,
    val date: Date?,
    val updatedAt: Date
)