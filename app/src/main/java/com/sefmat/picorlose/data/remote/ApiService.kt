package com.sefmat.picorlose.data.remote

import com.sefmat.picorlose.data.model.PictureModel
import retrofit2.http.GET

interface ApiService {
    @GET("picture")
    suspend fun getPicture(): List<PictureModel>
}