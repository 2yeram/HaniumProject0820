package com.siyeon.haniumproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.google.android.material.bottomsheet.BottomSheetBehavior

import android.os.Build
import android.app.NotificationManager
import android.app.NotificationChannel
import android.content.Context
import androidx.core.app.NotificationCompat

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button = findViewById<Button>(R.id.bt_place_name)
        button.setOnClickListener {
            val bottomSheet = BottomSheetDialog()
            bottomSheet.show(supportFragmentManager, bottomSheet.tag)
        }

        var buttonAlarmActivity = findViewById<Button>(R.id.bt_home_alarm)
        buttonAlarmActivity.setOnClickListener {
            var intent = Intent(applicationContext, AlarmActivity::class.java)
            startActivity(intent)
        }

        var buttonCCTVActivity = findViewById<Button>(R.id.bt_home_cctv)
        buttonCCTVActivity.setOnClickListener {
            var intent = Intent(applicationContext, CctvActivity::class.java)
            startActivity(intent)
        }

        var buttonGalleryActivity = findViewById<Button>(R.id.bt_home_gallery)
        buttonGalleryActivity.setOnClickListener {
            var builder = NotificationCompat.Builder(this, "MY_channel")
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle("알림 제목")
                .setContentText("알림 내용")

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val channel_id = "MY_channel" // 알림을 받을 채널 id 설정
                val channel_name = "DING DONG DANG" // 채널 이름 설정
                val descriptionText = "키즈카페에서 위험 상황이 발생했습니다." // 채널 설명글 설정
                val importance = NotificationManager.IMPORTANCE_DEFAULT // 알림 우선순위 설정
                val channel = NotificationChannel(channel_id, channel_name, importance).apply {
                    description = descriptionText
                }

                // 알림 표시
                val notificationManager: NotificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
                notificationManager.createNotificationChannel(channel)
                notificationManager.notify(1002, builder.build())
            }
        }

        var buttonFacilityActivity = findViewById<Button>(R.id.bt_home_facility)
        buttonFacilityActivity.setOnClickListener {
            var intent = Intent(applicationContext, FacilityActivity::class.java)
            startActivity(intent)
        }
    }

}

