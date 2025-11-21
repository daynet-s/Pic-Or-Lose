package com.sefmat.picorlose.data.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RfInstance {
    val api: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl("")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}