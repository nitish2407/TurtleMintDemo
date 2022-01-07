package com.githubissuedemo.network

import com.google.gson.JsonArray
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.GET
import retrofit2.http.Url

interface ApiDataService {
    @get:GET("issues")
    val apiRequestsIssues: Call<JsonArray>

    @GET
    fun apiRequestsComments(
        @Url commentUrl: String
    ): Call<JsonArray>

}

