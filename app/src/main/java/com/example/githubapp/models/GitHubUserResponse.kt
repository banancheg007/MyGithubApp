package com.example.githubapp.models

import com.google.gson.annotations.SerializedName

data class GitHubUserResponse(
    @SerializedName("login") val login: String,
    @SerializedName("id") val id: Int,
    @SerializedName("avatar_url") val avatar: String,
    @SerializedName("html_url") val url: String,
    @SerializedName("name") val name: String,
    @SerializedName("public_repos") val repositories: Int,
    @SerializedName("public_gists") val gists: Int,
    @SerializedName("followers") val followers: Int,
    @SerializedName("following") val following: Int

)