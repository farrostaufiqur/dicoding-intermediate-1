package com.belajar.storyapp.ui.login

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.belajar.storyapp.model.LoginBody
import com.belajar.storyapp.network.api.ApiConfig
import com.belajar.storyapp.network.response.AuthResponse
import com.belajar.storyapp.util.AppPreferences
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginViewModel (private val pref: AppPreferences) : ViewModel() {

    private val _isLogin = MutableLiveData<Boolean>()
    val isLogin: MutableLiveData<Boolean> = _isLogin

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: MutableLiveData<Boolean> = _isLoading

    private val _isError = MutableLiveData<Boolean>()
    val isError: MutableLiveData<Boolean> = _isError

    fun login(body: LoginBody){
        _isLoading.value = true
        val client= ApiConfig.getApiService().login(body)
        client.enqueue(object : Callback<AuthResponse> {
            override fun onResponse(
                call: Call<AuthResponse>,
                response: Response<AuthResponse>
            ){
                _isLoading.value = false
                _isError.value = false
                _isLogin.value = false

                if(response.isSuccessful) {
                    val loginResult = response.body()!!.loginResult
                    val token = loginResult.token
                    val name = loginResult.name
                    val userId = loginResult.userId
                    val email = body.email

                    Log.d(TAG, "Token: $token, Name: $name, Email: $email, UserId: $userId")

                    saveUserToken(token)
                    saveUserId(userId)
                    saveUserName(name)
                    saveUserEmail(email)

                    _isLogin.value = true
                } else {
                    _isError.value = true
                }
            }

            override fun onFailure(call: Call<AuthResponse>, t: Throwable) {
                val message = "onFailure: ${t.message.toString()}"
                _isError.value = true
                Log.e(TAG, message)
            }
        })
    }

    fun saveUserToken(token: String){
        viewModelScope.launch {
            pref.saveUserToken(token)
        }
    }

    fun saveUserName(name: String){
        viewModelScope.launch {
            pref.saveUserName(name)
        }
    }

    fun saveUserEmail(email: String){
        viewModelScope.launch {
            pref.saveUserEmail(email)
        }
    }

    fun saveUserId(id: String){
        viewModelScope.launch {
            pref.saveUserId(id)
        }
    }

    companion object{
        private const val TAG = "LoginVM"
    }
}