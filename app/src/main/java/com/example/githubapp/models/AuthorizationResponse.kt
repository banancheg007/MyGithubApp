package com.example.githubapp.models

import com.google.gson.annotations.SerializedName

data class AuthorizationResponse(
    @SerializedName("login") val login: String,
    @SerializedName("code") val code: Int
)