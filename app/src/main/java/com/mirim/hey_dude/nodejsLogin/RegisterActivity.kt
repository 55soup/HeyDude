package com.mirim.hey_dude.nodejsLogin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.hey_dude.databinding.ActivityRegisterBinding
import com.mirim.hey_dude.RetroInterface
import retrofit2.Call
import retrofit2.Response

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    val api = RetroInterface.create()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.registerBtn.setOnClickListener {
            binding.apply {
                val name = inputName.text.toString()
                val id = inputID.text.toString()
                val pw = inputPW.text.toString()
                val checkPW = checkPW.text.toString()

                if(name == "") {
                    Toast.makeText(applicationContext, "닉네임을 입력해주세요", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }
                if(id == "") {
                    Toast.makeText(applicationContext, "아이디를 입력해주세요", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }
                if(pw == "") {
                    Toast.makeText(applicationContext, "비밀번호를 입력해주세요", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }
                if(checkPW != pw) {
                    Toast.makeText(applicationContext, "비밀번호가 다릅니다.", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }
            }
            val newUser = RegisterModel(
                binding.inputName.text.toString(),
                binding.inputID.text.toString(),
                binding.inputPW.text.toString()
            )

            api.register(newUser).enqueue(object: retrofit2.Callback<RegisterResult>{
                override fun onResponse(call: Call<RegisterResult>, response: Response<RegisterResult>) {
                    val result = response.body()?.message ?: return
                    if(result) {
                        Toast.makeText(applicationContext, "회원가입 성공", Toast.LENGTH_SHORT).show()
                    }else {
                        Toast.makeText(
                            applicationContext,
                            "회원가입 실패, 이미 존재하는 아이디 입니다.",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }

                override fun onFailure(call: Call<RegisterResult>, t: Throwable) {
                    Log.d("test", t.message.toString())
                }
            })
        } //회원가입 버튼 클릭 이벤트



    } //onCreate()
} //class RegisterActivity
