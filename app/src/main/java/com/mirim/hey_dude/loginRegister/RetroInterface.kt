package com.mirim.hey_dude

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.mirim.hey_dude.loginRegister.*
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import java.io.Serializable
import java.util.*

interface RetroInterface{
    @POST("/register")
    @Headers("accept: application/json",
        "content-type: application/json")
    fun register(
        @Body jsonparams: RegisterModel
    ) : Call<RegisterResult>

    @POST("/login")
    fun login(
        @Body jsonparams: LoginModel
    ) : Call<LoginResult>

    @GET("/users_info")
    fun allUser(): Call<ArrayList<User>>

    companion object { // static 처럼 공유객체로 사용가능함. 모든 인스턴스가 공유하는 객체로서 동작함.
        private const val BASE_URL = "http://본인 컴퓨터 IP 주소:포트번호" //

        fun create(): RetroInterface {
            val gson : Gson =   GsonBuilder().setLenient().create();

            return Retrofit.Builder()
                .baseUrl(BASE_URL)
//                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
                .create(RetroInterface::class.java)
        }
    }

    data class RegisterModel(
        var name: String,
        var id: String,
        var pw: String
    )

    data class RegisterResult(
        var message: Boolean
    )

    data class LoginModel(
        var id: String,
        var pw: String
    )

    data class LoginResult(
        var UID: Int
    )

    data class User(
        val UID: Int,
        val id: String,
        val password: String
    ) : Serializable
}
