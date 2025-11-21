package com.sefmat.picorlose.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.sefmat.picorlose.data.model.ErrorMsg
import com.sefmat.picorlose.data.model.LoginModel
import com.sefmat.picorlose.repository.LoginRepository

class LoginVM : ViewModel() {
    private val repository = LoginRepository()

    var login: LoginModel by mutableStateOf(repository.getLogin())
    var errorMsg: ErrorMsg by mutableStateOf(repository.getErrorMsgs())

    fun verifyName(): Boolean {
        if (repository.validUsername()) {
            errorMsg.username = ""
            return true
        }
        else {
            errorMsg.username = "Nombre de usuario no existe"
            return false
        }
        return repository.validUsername()
    }

    fun verifyPassword(): Boolean {
        if (repository.validPassword()) {
            errorMsg.password = ""
            return true
        }
        else {
            errorMsg.password = "Contraseña incorrecta"
            return false
        }
        return repository.validPassword()
    }

    fun verifyLogin(): Boolean {
        return verifyName() && verifyPassword()
    }
}