//package com.mirim.hey_dude
//
//import android.telecom.Call
//import com.mirim.hey_dude.loginRegister.*
//import retrofit2.http.Body
//import retrofit2.http.GET
//import retrofit2.http.Headers
//import retrofit2.http.POST
//import java.util.*
//
//interface RetroInterface {
//    @POST("/register")
//    @Headers("accept: application/json",
//    "content-type: application/json")
//    fun register(
//        @Body jsonparams: RegisterModel
//    ) : Call<RegisterResult>
//
//    @POST("/login")
//    fun login(
//        @Body jsonparams: LoginModel
//    ) : Call<LoginResult>
//
//    @GET("/users_info")
//    fun allUser(): Call<ArrayList<User>>
//
//    companion object {
//        private const val BASE_URL = "http://본인 컴퓨터 IP주소:포트번호"
//    }
//}

