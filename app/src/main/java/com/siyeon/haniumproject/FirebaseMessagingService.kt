package com.siyeon.haniumproject

import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class FirebaseMessagingService : FirebaseMessagingService() {

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)

        // 수신된 알림 메시지 처리
        Log.d(TAG, "From: ${remoteMessage.from}")

        // 알림 메시지 데이터 가져오기
        if (remoteMessage.data.isNotEmpty()) {
            Log.d(TAG, "Message data payload: " + remoteMessage.data)
            // 여기에서 데이터를 추출하고 원하는 작업을 수행합니다.
        }

        // 알림 메시지 표시
        if (remoteMessage.notification != null) {
            Log.d(TAG, "Message Notification Body: ${remoteMessage.notification?.body}")
            // 여기에서 알림을 표시하거나 원하는 작업을 수행합니다.
        }
    }

    override fun onNewToken(token: String) {
        super.onNewToken(token)
        // 새 토큰 생성 시에 호출되며 토큰을 서버에 업데이트하거나 다른 작업을 수행할 수 있습니다.
        Log.d(TAG, "Refreshed token: $token")
    }

    companion object {
        private const val TAG = "FirebaseMessagingService"
    }
}