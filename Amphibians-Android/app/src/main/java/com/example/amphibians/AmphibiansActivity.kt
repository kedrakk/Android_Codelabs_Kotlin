package com.example.amphibians

import android.app.Application
import com.example.amphibians.data.IRetrofitInstance
import com.example.amphibians.data.RetrofitInstance

class AmphibiansApplication: Application() {

    lateinit var retrofitInstance: IRetrofitInstance

    override fun onCreate() {
        super.onCreate()
        retrofitInstance = RetrofitInstance()
    }
}