package com.vitoksmile.reminder.di.modules

import com.vitoksmile.reminder.data.db.AppDatabase
import com.vitoksmile.reminder.data.repository.NotesRepository
import com.vitoksmile.reminder.data.repository.NotesRepositoryImpl
import org.koin.dsl.module

internal val dataModule = module {
    single { AppDatabase.getInstance(get()).getNotesDao() }
    factory<NotesRepository> { NotesRepositoryImpl(get()) }
}