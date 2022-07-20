package com.belajar.storyapp.network.api

import com.belajar.storyapp.model.LoginBody
import com.belajar.storyapp.model.RegisterBody
import com.belajar.storyapp.network.response.AuthResponse
import com.belajar.storyapp.network.response.StoriesResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*

//
interface ApiService {

    @POST("register")
    fun register(
        @Body registerBody: RegisterBody
    ): Call<AuthResponse>

    @POST("login")
    fun login(
        @Body loginBody: LoginBody
    ): Call<AuthResponse>

    @GET("stories")
    fun getAllStories(
        @Header("Authorization") token: String
    ): Call<StoriesResponse>

    @Multipart
    @POST("stories")
    fun postStories(
        @Header("Authorization") token: String,
        @Part file: MultipartBody.Part,
        @Part("description") description: RequestBody,
    ): Call<StoriesResponse>
}