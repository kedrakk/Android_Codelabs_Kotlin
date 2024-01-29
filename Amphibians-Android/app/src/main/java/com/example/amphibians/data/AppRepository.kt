package com.example.amphibians.data

import com.example.amphibians.model.Amphibians
import com.example.amphibians.network.ApiService

interface IAppRepository {
    suspend fun getAllAmphibians():List<Amphibians>
}

class AppRepository(private val apiService: ApiService):IAppRepository{

    override suspend fun getAllAmphibians(): List<Amphibians> {
        return apiService.getAllAmphibians();
    }

}