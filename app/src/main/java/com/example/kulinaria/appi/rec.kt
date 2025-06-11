package com.example.kulinaria.appi

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object rec {
    private lateinit var okHttpClient: OkHttpClient
    private lateinit var retrofit: Retrofit
    private lateinit var receptapi: receptapi

    private val loggingInterceptor = HttpLoggingInterceptor()

    fun init() {


        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)


        okHttpClient = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()


        retrofit = Retrofit.Builder()
            .baseUrl("https://raw.githubusercontent.com/LukaLominadze/GameDrive/api/api/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        receptapi = retrofit.create(receptapi::class.java)
    }

    fun getrecepti(): receptapi {
        if (!::receptapi.isInitialized) {
            throw IllegalStateException("RestClient not initialized. Call RestClient.init() first.")
        }
        return receptapi
    }
}