package com.example.githubapp.models

import com.google.gson.annotations.SerializedName

data class GitHubUserResponse(
    @SerializedName("login") val login: String,
    @SerializedName("avatar_url") val avatar: String,
    @SerializedName("html_url") val pageUrl: String,
    @SerializedName("name") val name: String,
    @SerializedName("public_repos") val repository: Int,
    @SerializedName("public_gists") val gist: Int,
    @SerializedName("followers") val followers: Int,
    @SerializedName("following") val following: Int
)