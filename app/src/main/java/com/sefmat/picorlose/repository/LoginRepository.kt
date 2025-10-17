package com.sefmat.picorlose.repository

import com.sefmat.picorlose.model.LoginModel

class LoginRepository {
    private var login = LoginModel()

    fun getLogin(): LoginModel = login

    // WIP
    fun validUsername(): Boolean {
        if(login.username != "")
            return true
        else
            return false
    }

    // WIP
    fun validPassword(): Boolean {
        if(login.password != "")
            return true
        else
            return false
    }
}