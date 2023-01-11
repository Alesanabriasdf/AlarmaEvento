package com.example.educacionitalarmaevento

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.icu.util.Calendar
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.util.Log

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager

        val intent = Intent(this, AlarmReceiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(this,0,intent,0)

        lanzarEvento(pendingIntent, alarmManager)

        lanzarEventoEnHorarioPredeterminado(pendingIntent, alarmManager)
    }

    private fun lanzarEventoEnHorarioPredeterminado(pendingIntent: PendingIntent, alarmManager: AlarmManager) {
        val calendar = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            Calendar.getInstance()
        } else {
            TODO("VERSION.SDK_INT < N")
            //Calendar.getInstance() para version < Build.VERSION_CODES.N
        }
        calendar.timeInMillis = System.currentTimeMillis()
        calendar.set(Calendar.HOUR_OF_DAY, 16)
        calendar.set(Calendar.MINUTE, 30)

        alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.timeInMillis, pendingIntent)
    }

    private fun lanzarEvento(pendingIntent: PendingIntent, alarmManager: AlarmManager) {
        Log.i("AlarmReceiver","Incia el conteo")

        alarmManager.set(
            AlarmManager.ELAPSED_REALTIME_WAKEUP,
            SystemClock.elapsedRealtime() + 20000,
            pendingIntent
        )
    }
}