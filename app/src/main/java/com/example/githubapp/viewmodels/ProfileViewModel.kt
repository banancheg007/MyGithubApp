package com.example.githubapp.viewmodels

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.util.Log
import com.example.githubapp.Constants
import com.example.githubapp.models.GitHubUserResponse
import com.example.githubapp.network.Repository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProfileViewModel(
    application: Application
) : AndroidViewModel(application) {

    private val repository = Repository()

    private val _githubResponse = MutableLiveData<GitHubUserResponse>()
    val githubResponse: LiveData<GitHubUserResponse> = _githubResponse



    fun makeRequest(position: Int) {
        Log.e("makeRequest", "request")
        fetchUser(Constants.studentGithubLogin[position])
    }

    fun makeRequest(user: String){
        fetchUser(user)
    }

    private fun fetchUser(user: String) {
        repository.getGitHubUser(user).enqueue(object : Callback<GitHubUserResponse> {
            override fun onResponse(call: Call<GitHubUserResponse>, response: Response<GitHubUserResponse>) {
                if (response.isSuccessful) {
                    Log.e("onResponse", "response")
                    _githubResponse.value = response.body()
                }
            }

            override fun onFailure(call: Call<GitHubUserResponse>, t: Throwable) {
            }
        })
    }

}