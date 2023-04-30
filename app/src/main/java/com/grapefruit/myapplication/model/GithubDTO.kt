package com.grapefruit.myapplication.model

data class GithubDTO(
    val avatar_url: String,
    val name: String,
    val login: String,
    val followers: Int,
    val following: Int,
    val bio: String
)
