package com.sefmat.picorlose.repository

import com.sefmat.picorlose.model.ErrorMsg
import com.sefmat.picorlose.model.LoginModel

class LoginRepository {
    private var login = LoginModel()
    private var errors = ErrorMsg()

    fun getLogin(): LoginModel = login
    fun getErrorMsgs(): ErrorMsg = errors

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