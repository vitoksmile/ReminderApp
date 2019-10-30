package com.vitoksmile.reminder

import android.app.Application
import com.vitoksmile.reminder.di.initDI
import com.vitoksmile.reminder.di.viewModelModule

class ReminderApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        initDI(viewModelModule)
    }
}