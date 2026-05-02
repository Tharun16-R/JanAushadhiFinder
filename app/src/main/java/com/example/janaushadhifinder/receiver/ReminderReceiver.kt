package com.example.janaushadhifinder.receiver

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import com.example.janaushadhifinder.R

class ReminderReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        val action = intent.action
        val medicineName = intent.getStringExtra("medicine_name") ?: "Medicine"
        val genericName = intent.getStringExtra("generic_name") ?: ""

        when (action) {
            "DAILY_REMINDER" -> {
                showNotification(
                    context,
                    "💊 Daily Medicine Reminder",
                    "Time to take $medicineName ($genericName)",
                    "Don't forget your daily dose for better health!"
                )
            }
            "MONTHLY_REMINDER" -> {
                showNotification(
                    context,
                    "📅 Medicine Refill Reminder",
                    "Time to refill $medicineName",
                    "Visit your nearest Jan Aushadhi store for affordable refills."
                )
            }
        }
    }

    private fun showNotification(context: Context, title: String, message: String, bigText: String) {
        val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        
        // Create notification channel for Android 8.0+
        val channelId = "medicine_reminders"
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                channelId,
                "Medicine Reminders",
                NotificationManager.IMPORTANCE_HIGH
            ).apply {
                description = "Notifications for medicine reminders and refills"
                enableLights(true)
                enableVibration(true)
            }
            notificationManager.createNotificationChannel(channel)
        }

        val notification = NotificationCompat.Builder(context, channelId)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle(title)
            .setContentText(message)
            .setStyle(NotificationCompat.BigTextStyle().bigText(bigText))
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setAutoCancel(true)
            .setDefaults(NotificationCompat.DEFAULT_ALL)
            .build()

        notificationManager.notify(System.currentTimeMillis().toInt(), notification)
    }
}
