package com.sefmat.picorlose.data.remote

import com.sefmat.picorlose.data.model.PictureModel
import com.sefmat.picorlose.data.model.UserAPI
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {
    @GET("picture")
    suspend fun getPicture(): List<PictureModel>

    // FUNCIONES API USERS
    @GET("user")
    suspend fun getUser(): List<UserAPI>

    @POST("create_user")
    suspend fun insertUser(@Body user: UserAPI): Response<UserAPI>
}