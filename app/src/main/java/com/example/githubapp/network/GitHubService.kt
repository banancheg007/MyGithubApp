package com.example.githubapp.network

import com.example.githubapp.models.AuthorizationResponse
import com.example.githubapp.models.GitHubUserResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface GitHubService {

    @GET("users/{user}")
    fun getUserDetails(@Path("user") user: String): Call<GitHubUserResponse>

    @GET("user")
    fun authUser(@Header("Authorization") authHeader: String): Call<AuthorizationResponse>

}