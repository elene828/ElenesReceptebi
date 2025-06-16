package com.example.kulinaria.appi

import com.example.kulinaria.models.Recepti
import retrofit2.Response
import retrofit2.http.GET

interface Recept_api {
    @GET("recepti.json")
    suspend fun getrecepti(): Response<List<Recepti>>

}