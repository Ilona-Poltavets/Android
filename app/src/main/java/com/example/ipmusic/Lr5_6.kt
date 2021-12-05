package com.example.ipmusic

import android.app.Notification
import android.app.NotificationManager
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat


class Lr5_6 : AppCompatActivity() {
    lateinit var mainHandler: Handler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lr56)
        mainHandler = Handler(Looper.getMainLooper())

        val btnStart=findViewById(R.id.startNotify) as Button
        val btnStop=findViewById(R.id.stopNotify) as Button
        btnStart.setOnClickListener {
            onResume()
        }
        btnStop.setOnClickListener {
            onPause()
        }
    }

    private val updateTextTask = object : Runnable {
        override fun run() {
            val builder = NotificationCompat.Builder(this@Lr5_6)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("Напоминание")
                .setContentText("Прошло 3 секунды)))")
                .setAutoCancel(true)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)

            val notification: Notification = builder.build()
            notification.defaults = Notification.DEFAULT_SOUND
            val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.notify(1, notification)
            mainHandler.postDelayed(this, 3000)
        }
    }

    override fun onPause() {
        super.onPause()
        mainHandler.removeCallbacks(updateTextTask)
    }

    override fun onResume() {
        super.onResume()
        mainHandler.post(updateTextTask)
    }
}