package com.mirim.hey_dude.nodejsLogin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import com.example.hey_dude.R
import com.example.hey_dude.databinding.ActivityLoginBinding
import com.mirim.hey_dude.NavBar
import com.mirim.hey_dude.RetroInterface
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    val api = RetroInterface.create()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //회원가입 버튼 클릭 시 회원가입 액티비티로 이동
        val registerBtn = findViewById<Button>(R.id.registerBtn)
        registerBtn.setOnClickListener {
//            val intent = Intent(this, RegisterActivity::class.java)
//            startActivity(intent)
        }

        binding.loginBtn.setOnClickListener {
            binding.apply {
                val id = loginID.text.toString()
                val pw = loginPW.text.toString()

                if(id == "") {
                    Toast.makeText(applicationContext, "아이디를 입력하세요", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }
                if(pw == "") {
                    Toast.makeText(applicationContext, "비밀번호를 입력하세요", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }
            }
            val loginUser = LoginModel(
                binding.loginID.text.toString(),
                binding.loginPW.text.toString()
            )
            api.login(loginUser).enqueue(object: Callback<LoginResult> {
                override fun onResponse(call: retrofit2.Call<LoginResult>, response: Response<LoginResult>) {
                    val user_uid = response.body()?.UID ?: return
                    if(user_uid != -1) {
                        Toast.makeText(applicationContext, "로그인 성공", Toast.LENGTH_SHORT).show()
                        val intent = Intent(this@LoginActivity, NavBar::class.java)
                        intent.putExtra("id", binding.loginID.text.toString())
                        startActivity(intent)

                        Log.d("testt", user_uid.toString())
                    }
                    else{
                        Toast.makeText(applicationContext, "로그인 실패, 아이디 또는 비밀번호를 확인해주세요.", Toast.LENGTH_SHORT).show()
                    }

                }

                override fun onFailure(call: retrofit2.Call<LoginResult>, t: Throwable) {
                    Log.d("test", t.message.toString())
                }
            })
        }

//        binding.allUserButton.setOnClickListener {
//            api.allUser().enqueue(object:Callback<ArrayList<RetroInterface.User>>{
//                override fun onResponse(
//                    call: retrofit2.Call<ArrayList<RetroInterface.User>>,
//                    response: Response<ArrayList<RetroInterface.User>>
//                ) {
//                    val userList = response.body() ?: return
//                    val intent = Intent(this@LoginActivity, AllUserActivity::class.java)
//                    intent.putExtra("userList", userList)
//                    startActivity(intent)
//                }
//
//                override fun onFailure(call: retrofit2.Call<ArrayList<RetroInterface.User>>, t: Throwable) {
//                    Log.d("testt",t.message.toString())
//                }
//            })
//        }

    }
}