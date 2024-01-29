package com.example.amphibians.network

import com.example.amphibians.model.Amphibians
import retrofit2.http.GET

interface ApiService {

    @GET("amphibians")
    suspend fun getAllAmphibians(): List<Amphibians>
}