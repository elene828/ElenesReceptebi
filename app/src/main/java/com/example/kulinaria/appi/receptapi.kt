package com.example.kulinaria.appi

import com.example.kulinaria.models.recepti
import retrofit2.Response
import retrofit2.http.GET

interface receptapi {
    @GET("recepti.json")
    suspend fun getrecepti(): Response<List<recepti>>

}