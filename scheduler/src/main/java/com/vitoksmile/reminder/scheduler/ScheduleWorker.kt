package com.vitoksmile.reminder.scheduler

import android.content.Context
import android.content.Intent
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters

internal class ScheduleWorker(
    private val appContext: Context,
    params: WorkerParameters
) : CoroutineWorker(appContext, params) {

    override suspend fun doWork(): Result {
        try {
            val task = inputData.toTask()
            val serviceIntent = Intent(appContext, Class.forName(task.serviceClassName)).apply {
                Scheduler.applyTaskId(this, task.id)
            }
            appContext.startService(serviceIntent)
        } catch (error: Throwable) {
            error.printStackTrace()
        }
        return Result.success()
    }
}