package com.mirim.hey_dude.nodejsLogin

import java.io.Serializable

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