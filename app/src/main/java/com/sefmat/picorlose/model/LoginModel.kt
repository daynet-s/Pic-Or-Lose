package com.sefmat.picorlose.model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

class LoginModel {
    // Nombre de Usuario
    var username by mutableStateOf("")
    // Contraseña
    var passwrd by mutableStateOf("")
}