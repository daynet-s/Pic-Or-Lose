package com.sefmat.picorlose.data.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// INSTANCIA RETROFIT IMAGENES
object RfInstance {
    val api: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl("https://x8ki-letl-twmt.n7.xano.io/api:bgOHyPHf/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}

// INSTANCIA RETROFIT USUARIOS
object RfInstance_Users {
    val api: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl("https://x8ki-letl-twmt.n7.xano.io/api:EPwWbsLj/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}