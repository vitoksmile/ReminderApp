package com.vitoksmile.reminder

import android.app.Application
import com.vitoksmile.reminder.di.initDI
import com.vitoksmile.reminder.di.viewModelModule

class ReminderApplication : Application() {

    companion object {

        @Volatile
        private lateinit var appContext: ReminderApplication

        fun appContext(): Application = appContext
    }

    override fun onCreate() {
        super.onCreate()
        appContext = this
        initDI(viewModelModule)
    }
}