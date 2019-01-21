package com.example.githubapp.viewmodels

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.util.Base64
import android.util.Log
import com.example.githubapp.models.AuthorizationResponse
import com.example.githubapp.network.Repository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginViewModel(
    application: Application
) : AndroidViewModel(application) {

    private val repository = Repository()

    private val _authResponse = MutableLiveData<AuthorizationResponse>()
    val authorizationResponse: LiveData<AuthorizationResponse> = _authResponse

    fun login(email: String, password: String) {
        val base = "$email:$password"
        val authHeader: String = "Basic " + Base64.encodeToString(base.toByteArray(), Base64.NO_WRAP)
        Log.d("my", authHeader)
        authorization(authHeader)
    }

    private fun authorization(authHeader: String) {
        repository.auth(authHeader).enqueue(object : Callback<AuthorizationResponse> {
            override fun onFailure(call: Call<AuthorizationResponse>, t: Throwable) {
                Log.e("onFailure", "${t.message}")
            }

            override fun onResponse(call: Call<AuthorizationResponse>, response: Response<AuthorizationResponse>) {
                if (response.isSuccessful) {
                    _authResponse.value = response.body()!!
                }
            }

        })
    }

}