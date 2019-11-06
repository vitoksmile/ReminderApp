package com.vitoksmile.reminder.notifications

import android.app.Service
import android.content.Intent
import android.os.IBinder
import com.vitoksmile.reminder.domain.models.Note
import com.vitoksmile.reminder.domain.usecases.NotesUseCase
import com.vitoksmile.reminder.scheduler.Scheduler
import kotlinx.coroutines.*
import org.koin.android.ext.android.inject
import kotlin.coroutines.CoroutineContext

class NotificationsService : Service(), CoroutineScope {

    private val job = SupervisorJob()
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job + CoroutineExceptionHandler { _, throwable -> throwable.printStackTrace() }

    private val notesUseCase: NotesUseCase by inject()

    override fun onBind(intent: Intent?): IBinder? = null

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        handleTask(intent, startId)
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        job.cancelChildren()
        super.onDestroy()
    }

    private fun handleTask(intent: Intent, startId: Int) = launch {
        val noteId = Scheduler.getTaskId(intent)
        val note = notesUseCase.getNote(noteId)
        showNotification(note)
        stopSelf(startId)
    }

    private fun showNotification(note: Note) {
        // TODO: not implemented
    }
}