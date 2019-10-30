package com.vitoksmile.reminder.di

import android.app.Application
import com.vitoksmile.reminder.di.modules.dataModule
import com.vitoksmile.reminder.di.modules.domainModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.module.Module

fun Application.initDI(vararg modules: Module) {
    startKoin {
        androidContext(this@initDI)
        modules(
            listOf(
                dataModule,
                domainModule
            ).plus(modules)
        )
    }
}