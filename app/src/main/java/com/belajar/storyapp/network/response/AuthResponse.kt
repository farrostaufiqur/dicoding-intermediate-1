package com.belajar.storyapp.network.response

import com.google.gson.annotations.SerializedName

data class AuthResponse (
    @field:SerializedName("error")
    val error: Boolean,
    @field:SerializedName("message")
    val message: String,
    @field:SerializedName("loginResult")
    val loginResult: User
){
    data class User(
        @SerializedName("userId")
        val userId: String,
        @SerializedName("name")
        val name: String,
        @SerializedName("token")
        val token: String
    )
}