package com.vitoksmile.reminder.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
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

    companion object {

        @Volatile
        private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase = synchronized(this) {
            instance ?: Room.databaseBuilder(context, AppDatabase::class.java, "reminder.db")
                .fallbackToDestructiveMigration()
                .build()
                .also { instance = it }
        }
    }
}