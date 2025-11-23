package com.sefmat.picorlose.repository

import com.sefmat.picorlose.data.model.PictureModel
import com.sefmat.picorlose.data.remote.RfInstance

class PictureRepository {
    suspend fun getPictures(): List<PictureModel> {
        return RfInstance.api.getPicture()
    }
}