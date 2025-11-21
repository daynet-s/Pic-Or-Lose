package com.sefmat.picorlose.data.model

data class PictureModel(
    val userId: Int,
    val picId: Int,
    val title: String,
    val picture: String // BASE64

    // SERAN LAS IMAGENES DE LA TEMATICA QUE HAYAN SIDO PUBLICADAS DURANTE EL DIA
)