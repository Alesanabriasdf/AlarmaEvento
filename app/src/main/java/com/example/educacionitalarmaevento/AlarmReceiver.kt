package com.example.educacionitalarmaevento

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log

class AlarmReceiver: BroadcastReceiver() {

    override fun onReceive(p0: Context?, p1: Intent?) {
        Log.d("AlarmReceiver","Evento ejectuado")
    }

}