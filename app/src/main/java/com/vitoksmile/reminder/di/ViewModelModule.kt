package com.vitoksmile.reminder.di

import com.vitoksmile.reminder.note.NoteManageViewModel
import com.vitoksmile.reminder.notes.NotesViewModel
import org.koin.dsl.module

internal val viewModelModule = module {
    factory { NotesViewModel(get()) }
    factory { NoteManageViewModel(get()) }
}