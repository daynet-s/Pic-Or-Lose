package com.sefmat.picorlose.repository

import com.sefmat.picorlose.model.LoginModel

class LoginRepository {
    private var login = LoginModel()

    fun getLogin(): LoginModel = login

    fun validUsername(): Boolean {
        return false // WIP
    }

    fun validPassword(): Boolean {
        return false // WIP
    }
}