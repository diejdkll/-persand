package com.grapefruit.myapplication.model

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface GithubAPI {
    @GET("users/{owner}")
    fun getGitInfo(
        @Path("owner") owner: String
    ): Call<GithubDTO>
}