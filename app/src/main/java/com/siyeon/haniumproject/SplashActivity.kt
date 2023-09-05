package com.siyeon.haniumproject

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.FirebaseApp

class SplashActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        // Firebase 초기화 (초기화 코드 추가)
        FirebaseApp.initializeApp(this)

        super.onCreate(savedInstanceState)
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }

}