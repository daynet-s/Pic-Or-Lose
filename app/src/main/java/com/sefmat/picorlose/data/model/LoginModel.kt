package com.sefmat.picorlose.data.model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.room.Entity
import androidx.room.PrimaryKey

// Habra que reemplazar LoginModel con UserModel???
class LoginModel {
    // Nombre de Usuario
    var username by mutableStateOf("")
    // Contraseña
    var password by mutableStateOf("")
}

@Entity(tableName = "users")
data class UserModel (
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val passwrd: String,
    val points: Int // PARA LA LEADERBOARD DEL MENU
)