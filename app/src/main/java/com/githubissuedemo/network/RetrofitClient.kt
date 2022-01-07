package com.githubissuedemo.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
        private val BASE_URL = "https://api.github.com/repos/square/okhttp/"
        private var retrofit: Retrofit? = null

        val service: ApiDataService
            get() {
                if (retrofit == null) {
                    retrofit = Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build()
                }

                return retrofit!!.create<ApiDataService>(ApiDataService::class.java!!)
            }
    }

