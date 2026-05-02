package com.example.janaushadhifinder.utils

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.widget.Toast
import com.example.janaushadhifinder.receiver.ReminderReceiver
import com.example.janaushadhifinder.model.Medicine

class ReminderManager(private val context: Context) {

    fun setDailyReminder(medicine: Medicine, hour: Int = 9, minute: Int = 0) {
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(context, ReminderReceiver::class.java).apply {
            action = "DAILY_REMINDER"
            putExtra("medicine_name", medicine.brandName)
            putExtra("generic_name", medicine.genericName)
        }

        val pendingIntent = PendingIntent.getBroadcast(
            context, 
            medicine.brandName.hashCode(), 
            intent, 
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        // Set for tomorrow at specified time
        val calendar = java.util.Calendar.getInstance().apply {
            timeInMillis = System.currentTimeMillis()
            add(java.util.Calendar.DAY_OF_MONTH, 1)
            set(java.util.Calendar.HOUR_OF_DAY, hour)
            set(java.util.Calendar.MINUTE, minute)
            set(java.util.Calendar.SECOND, 0)
        }

        try {
            alarmManager.setRepeating(
                AlarmManager.RTC_WAKEUP,
                calendar.timeInMillis,
                AlarmManager.INTERVAL_DAY,
                pendingIntent
            )
            Toast.makeText(context, "Daily reminder set for ${medicine.brandName}", Toast.LENGTH_SHORT).show()
        } catch (e: SecurityException) {
            Toast.makeText(context, "Please enable alarm permissions", Toast.LENGTH_LONG).show()
        }
    }

    fun setMonthlyRefillReminder(medicine: Medicine, dayOfMonth: Int = 1) {
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(context, ReminderReceiver::class.java).apply {
            action = "MONTHLY_REMINDER"
            putExtra("medicine_name", medicine.brandName)
            putExtra("generic_name", medicine.genericName)
        }

        val pendingIntent = PendingIntent.getBroadcast(
            context, 
            "monthly_${medicine.brandName}".hashCode(), 
            intent, 
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        // Set for next month on specified day
        val calendar = java.util.Calendar.getInstance().apply {
            timeInMillis = System.currentTimeMillis()
            add(java.util.Calendar.MONTH, 1)
            set(java.util.Calendar.DAY_OF_MONTH, dayOfMonth)
            set(java.util.Calendar.HOUR_OF_DAY, 9)
            set(java.util.Calendar.MINUTE, 0)
            set(java.util.Calendar.SECOND, 0)
        }

        try {
            alarmManager.setRepeating(
                AlarmManager.RTC_WAKEUP,
                calendar.timeInMillis,
                AlarmManager.INTERVAL_DAY * 30, // Approximate monthly
                pendingIntent
            )
            Toast.makeText(context, "Monthly refill reminder set for ${medicine.brandName}", Toast.LENGTH_SHORT).show()
        } catch (e: SecurityException) {
            Toast.makeText(context, "Please enable alarm permissions", Toast.LENGTH_LONG).show()
        }
    }

    fun cancelReminder(medicine: Medicine) {
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        
        // Cancel daily reminder
        val dailyIntent = Intent(context, ReminderReceiver::class.java).apply {
            action = "DAILY_REMINDER"
        }
        val dailyPendingIntent = PendingIntent.getBroadcast(
            context, 
            medicine.brandName.hashCode(), 
            dailyIntent, 
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )
        alarmManager.cancel(dailyPendingIntent)

        // Cancel monthly reminder
        val monthlyIntent = Intent(context, ReminderReceiver::class.java).apply {
            action = "MONTHLY_REMINDER"
        }
        val monthlyPendingIntent = PendingIntent.getBroadcast(
            context, 
            "monthly_${medicine.brandName}".hashCode(), 
            monthlyIntent, 
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )
        alarmManager.cancel(monthlyPendingIntent)

        Toast.makeText(context, "Reminders cancelled for ${medicine.brandName}", Toast.LENGTH_SHORT).show()
    }
}
