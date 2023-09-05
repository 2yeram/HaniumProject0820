package com.siyeon.haniumproject

import Users
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.FirebaseApp
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.siyeon.haniumproject.LoginActivity
import com.siyeon.haniumproject.R
import com.siyeon.haniumproject.databinding.ActivityRegisterBinding

class Register : AppCompatActivity() {

    private val TAG: String = "RegisterActivity"
    private lateinit var binding: ActivityRegisterBinding
    private var isExistBlank = false
    private var isPWSame = false

    // 파이어베이스 데이터베이스 연동
    private val database: FirebaseDatabase = FirebaseDatabase.getInstance()

    var register_register: Button? = null
    var register_id: EditText? = null
    var register_pw: EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {

        // Firebase 초기화 (초기화 코드 추가)
        FirebaseApp.initializeApp(this)

        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        register_register = findViewById(R.id.register_register)
        register_id = findViewById(R.id.register_id)
        register_pw = findViewById(R.id.register_pw)

        binding.registerRegister.setOnClickListener {
            val registerid = binding.registerId.text.toString()
            val registerpw = binding.registerPw.text.toString()
            val pwRe = binding.registerPwRe.text.toString()

            if (registerid.isEmpty() || registerpw.isEmpty() || pwRe.isEmpty()) {
                isExistBlank = true
            } else {
                if (registerpw == pwRe) {
                    isPWSame = true
                }
            }

            if (!isExistBlank && isPWSame) {
                val sharedPreference = getSharedPreferences("file name", Context.MODE_PRIVATE)
                val editor = sharedPreference.edit()
                editor.putString("id", registerid)
                editor.putString("pw", registerpw)
                editor.apply()

                // Firebase Realtime Database로 데이터를 추가하는 함수 호출
                addUsers(registerid, registerpw)

                Toast.makeText(this, "회원가입 성공", Toast.LENGTH_SHORT).show()

                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)

            } else {

                if (isExistBlank) {
                    dialog("blank")
                } else if (!isPWSame) {
                    dialog("not same")
                }
            }
        }
    }

    // Firebase Realtime Database로 데이터를 추가하는 함수
    fun addUsers(id: String?, pw: String?) {
        // 여기에서 직접 변수를 만들어서 값을 직접 넣는 것도 가능합니다.
        // 예를 들어, 갓 태어난 동물만 입력하는 경우 int age = 1; 등을 넣을 수 있습니다.

        if (id != null && pw != null) {
            val users = Users(id, pw) //Users 클래스의 객체 생성
            val databaseReference: DatabaseReference = database.reference.child("users").child(id)
            // DatabaseReference에 데이터를 추가합니다. / DatabaseReference를 사용하여 데이터베이스 경로에 Users 객체 저장
            databaseReference.setValue(users)
        }
    }

    // 회원가입 실패시 다이얼로그를 띄워주는 메소드
    fun dialog(type: String) {
        val dialog = AlertDialog.Builder(this)

        if (type == "blank") {
            dialog.setTitle("회원가입 실패")
            dialog.setMessage("입력란을 모두 작성해주세요")
        } else if (type == "not same") {
            dialog.setTitle("회원가입 실패")
            dialog.setMessage("비밀번호가 다릅니다")
        }

        val dialog_listener = DialogInterface.OnClickListener { _, which ->
            when (which) {
                DialogInterface.BUTTON_POSITIVE ->
                    Log.d(TAG, "다이얼로그")
            }
        }

        dialog.setPositiveButton("확인", dialog_listener)
        dialog.show()
    }
}
