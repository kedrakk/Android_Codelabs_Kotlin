package com.example.amphibians.data

import com.example.amphibians.network.ApiService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

interface IRetrofitInstance {
    val appRepository: IAppRepository
}

class RetrofitInstance : IRetrofitInstance {
    private val BASE_URL = "https://android-kotlin-fun-mars-server.appspot.com/"

    private val contentType = "application/json".toMediaType()

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(Json.asConverterFactory(contentType))
        .build()

    private val retrofitService : ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }

    override val appRepository: IAppRepository by lazy {
        AppRepository(retrofitService)
    }


}