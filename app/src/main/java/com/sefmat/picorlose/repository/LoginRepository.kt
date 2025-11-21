package com.sefmat.picorlose.repository

import com.sefmat.picorlose.data.model.ErrorMsg
import com.sefmat.picorlose.data.model.LoginModel

class LoginRepository {
    private var login = LoginModel()
    private var errors = ErrorMsg()

    fun getLogin(): LoginModel = login
    fun getErrorMsgs(): ErrorMsg = errors

    // Habra que cambiar las funciones de abajo para que funcionen con AppDatabase

    // USUARIO Y CONTRASEÑA TEMPORALES
    fun validUsername(): Boolean {
        if(login.username == "pepito123")
            return true
        else
            return false
    }

    fun validPassword(): Boolean {
        if(login.password == "guh321")
            return true
        else
            return false
    }
}