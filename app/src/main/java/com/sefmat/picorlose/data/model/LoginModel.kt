package com.sefmat.picorlose.data.model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.room.Entity
import androidx.room.PrimaryKey

// MODELO USUARIO DE PRUEBA
class LoginModel {
    // Nombre de Usuario
    var username by mutableStateOf("")
    // Contraseña
    var password by mutableStateOf("")
}

// MODELO USUARIO LOCAL
@Entity(tableName = "users")
data class UserModel (
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val passwrd: String,
    val points: Int
)

// MODELO USUARIO API
data class UserAPI (
    val name: String,
    val passwrd: String,
    val points: Int,
    val email: String? = null  // TEMPORAL
)