package com.vitoksmile.reminder.data.db.converters

import androidx.room.TypeConverter
import java.util.*

class DateConverter {

    @TypeConverter
    fun toDB(date: Date) = date.time

    @TypeConverter
    fun toEntity(time: Long) = Date(time)
}