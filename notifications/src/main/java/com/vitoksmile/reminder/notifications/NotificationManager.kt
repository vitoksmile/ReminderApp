package com.vitoksmile.reminder.notifications

import android.app.Notification
import android.app.NotificationChannel
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

interface NotificationManager {

    fun show(id: Int, title: String, body: String)
}

private class NotificationManagerImpl(private val context: Context) : NotificationManager {

    private val manager = NotificationManagerCompat.from(context)
    private val channelId get() = getString(R.string.notification_channel_id)

    override fun show(id: Int, title: String, body: String) {
        initChannel()
        manager.notify(id, createNotification(title, body))
    }

    private fun initChannel() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) return

        val name = getString(R.string.notification_channel_name)
        val importance = android.app.NotificationManager.IMPORTANCE_HIGH
        val channel = NotificationChannel(channelId, name, importance)
        channel.lockscreenVisibility = Notification.VISIBILITY_SECRET
        channel.enableLights(true)
        channel.enableVibration(true)
        manager.createNotificationChannel(channel)
    }

    private fun createNotification(title: String, body: String) =
        NotificationCompat.Builder(context, channelId)
            .setContentTitle(title)
            .setContentText(body)
            .setSmallIcon(R.drawable.ic_notification)
            .setStyle(
                NotificationCompat.BigTextStyle()
                    .bigText(body)
            )
            .setAutoCancel(false)
            .build()

    private fun getString(resId: Int) = context.getString(resId)
}

fun Context.notificationManager(): Lazy<NotificationManager> = lazy {
    NotificationManagerImpl(applicationContext)
}