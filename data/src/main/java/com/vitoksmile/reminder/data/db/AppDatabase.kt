package com.vitoksmile.reminder.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.vitoksmile.reminder.data.db.converters.DateConverter
import com.vitoksmile.reminder.data.db.dao.NotesDao
import com.vitoksmile.reminder.data.db.entity.NoteEntity

@Database(
    entities = [
        NoteEntity::class
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(
    DateConverter::class
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun getNotesDao(): NotesDao
}