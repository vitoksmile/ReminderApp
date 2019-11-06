package com.vitoksmile.reminder.scheduler

import android.content.Context
import android.content.Intent
import androidx.work.*
import java.util.*
import java.util.concurrent.TimeUnit

class Scheduler private constructor(
    private val appContext: Context
) {

    companion object {

        private const val KEY_TASK_ID = "KEY_TASK_ID"

        @Volatile
        private var instance: Scheduler? = null

        fun init(appContext: Context) = synchronized(this) {
            instance ?: Scheduler(appContext).also { instance = it }
        }

        fun schedule(task: Task) {
            checkInstance()
            instance?.schedule(task)
        }

        fun unSchedule(taskId: Long) {
            checkInstance()
            instance?.unSchedule(taskId)
        }

        fun getTaskId(intent: Intent) = intent.getLongExtra(KEY_TASK_ID, -1)

        internal fun applyTaskId(intent: Intent, taskId: Long) {
            intent.putExtra(KEY_TASK_ID, taskId)
        }

        private fun checkInstance() {
            checkNotNull(instance) { "Scheduler was't initialized. Call Scheduler.init before schedule some task." }
        }
    }

    private val manager by lazy { WorkManager.getInstance(appContext) }

    fun schedule(task: Task) {
        unSchedule(task.id)

        val now = Calendar.getInstance()
        val time = Calendar.getInstance().apply {
            time = task.time
        }
        val duration = time.timeInMillis - now.timeInMillis
        if (duration < 0) return

        val work = OneTimeWorkRequestBuilder<ScheduleWorker>()
            .setInitialDelay(duration, TimeUnit.MILLISECONDS)
            .setInputData(task.toData())
            .build()
        val uniqueWorkName = task.id.asUniqueWorkName()
        manager.enqueueUniqueWork(uniqueWorkName, ExistingWorkPolicy.REPLACE, work)
    }

    fun unSchedule(taskId: Long) {
        manager.cancelUniqueWork(taskId.asUniqueWorkName())
    }

    private fun Long.asUniqueWorkName() = "SchedulerTask#$this"
}