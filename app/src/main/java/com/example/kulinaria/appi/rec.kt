package com.example.kulinaria.appi

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Rec {
    private lateinit var okHttpClient: OkHttpClient
    private lateinit var retrofit: Retrofit
    private lateinit var receptap: Recept_api

    private val loggingInterceptor = HttpLoggingInterceptor()

    fun init() {


        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)


        okHttpClient = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()


        retrofit = Retrofit.Builder()
            .baseUrl("https://raw.githubusercontent.com/elene828/ElenesReceptebi/main/app/src/main/res/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        receptap = retrofit.create(Recept_api::class.java)
    }

    fun getrecepti(): Recept_api {
        if (!::receptap.isInitialized) {
            throw IllegalStateException("RestClient not initialized. Call RestClient.init() first.")
        }
        return receptap
    }
}