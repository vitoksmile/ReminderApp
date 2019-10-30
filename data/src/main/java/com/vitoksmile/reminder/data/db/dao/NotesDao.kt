package com.vitoksmile.reminder.data.db.dao

import androidx.room.*
import com.vitoksmile.reminder.data.db.entity.NoteEntity
import kotlinx.coroutines.flow.Flow

@Suppress("FunctionName")
@Dao
interface NotesDao {

    @Query("SELECT * FROM notes ORDER BY date DESC")
    fun getNotes(): Flow<List<NoteEntity>>

    @Query("SELECT * FROM notes WHERE id=:id")
    suspend fun get(id: Long): NoteEntity

    @Transaction
    suspend fun insertOrUpdate(note: NoteEntity) {
        if (_insert(note) == -1L) _update(note)
    }

    @Query("DELETE FROM notes WHERE id=:id")
    suspend fun delete(id: Long)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun _insert(note: NoteEntity): Long

    @Update
    suspend fun _update(note: NoteEntity)
}