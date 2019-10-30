package com.vitoksmile.reminder.di.modules

import com.vitoksmile.reminder.domain.usecases.NotesUseCase
import com.vitoksmile.reminder.domain.usecases.NotesUseCaseImpl
import org.koin.dsl.module

internal val domainModule = module {
    factory<NotesUseCase> { NotesUseCaseImpl(get()) }
}