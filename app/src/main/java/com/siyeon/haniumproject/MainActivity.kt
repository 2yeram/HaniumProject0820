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
            var intent = Intent(applicationContext, GalleryActivity::class.java)
            startActivity(intent)
            }

        var buttonFacilityActivity = findViewById<Button>(R.id.bt_home_facility)
        buttonFacilityActivity.setOnClickListener {
            var intent = Intent(applicationContext, FacilityActivity::class.java)
            startActivity(intent)
        }
    }


}

