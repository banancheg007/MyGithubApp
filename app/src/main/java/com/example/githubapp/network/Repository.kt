package com.example.githubapp.network

import com.example.githubapp.models.AuthorizationResponse
import com.example.githubapp.models.GitHubUserResponse
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Repository {

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://api.github.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val gitHubService = retrofit.create(GitHubService::class.java)

    fun getGitHubUser(user: String): Call<GitHubUserResponse> {
        return gitHubService.getUserDetails(user)
    }

    fun auth(authHeader: String): Call<AuthorizationResponse> {
        return gitHubService.authUser(authHeader)
    }

}