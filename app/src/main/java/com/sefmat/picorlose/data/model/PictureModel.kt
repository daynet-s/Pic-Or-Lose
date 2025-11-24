package com.sefmat.picorlose.data.model

data class PictureModel(
    val user_id: Int,
    val pic_id: Int,
    val title: String,
    val picture: PictureDetails
)

data class PictureDetails(
    val access: String,
    val path: String,
    val name: String,
    val type: String,
    val size: Int,
    val mime: String,
    val meta: PictureMeta,
    val url: String
)

data class PictureMeta(
    val width: Int,
    val height: Int
)