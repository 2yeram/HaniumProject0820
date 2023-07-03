package com.siyeon.haniumproject

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class FacilityActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.facility_layout)

        title = "시설물 현황"

        var buttonReturn = findViewById<Button>(R.id.button_return)
        buttonReturn.setOnClickListener {
            finish()
        }
    }
}