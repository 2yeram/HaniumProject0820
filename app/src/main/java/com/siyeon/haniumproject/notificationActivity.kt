package com.siyeon.haniumproject

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import com.google.firebase.FirebaseApp
import com.google.firebase.database.*

class NotificationActivity : AppCompatActivity() {

    private lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notification)

        FirebaseApp.initializeApp(this)

        // Firebase Realtime Database의 데이터를 가져올 레퍼런스 설정
        databaseReference = FirebaseDatabase.getInstance().getReference("test/push")

        // ValueEventListener를 사용하여 데이터 가져오기
        databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val pushValue = dataSnapshot.getValue(Long::class.java)?.toString()

                if (pushValue == "1") {
                    // 알림을 표시하는 코드 호출
                    showNotification(applicationContext)
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                val errorMessage = databaseError.message
                Log.e("FirebaseDatabase", "Firebase Database 오류: $errorMessage")
                Toast.makeText(applicationContext, "Firebase Database 오류: $errorMessage", Toast.LENGTH_SHORT).show()
            }
        })
    }

    // 알림 표시 함수
    private fun showNotification(context: Context) {
        val builder = NotificationCompat.Builder(context, "MY_channel")
            .setSmallIcon(R.drawable.cctv_resize) // 알림 아이콘 설정
            .setContentTitle("알림 제목")
            .setContentText("알림 내용")

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel_id = "MY_channel"
            val channel_name = "DING DONG DANG"
            val descriptionText = "키즈카페에서 위급 상황이 발생했습니다."
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(channel_id, channel_name, importance).apply {
                description = descriptionText
            }

            val notificationManager: NotificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)

            val notification = builder.build()
            notificationManager.notify(1002, notification)
        }
    }
}
