package com.sefmat.picorlose.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.sefmat.picorlose.model.LoginModel
import com.sefmat.picorlose.repository.LoginRepository

class LoginVM : ViewModel() {
    private val repository = LoginRepository()

    var login: LoginModel by mutableStateOf(repository.getLogin())

    fun verifyName(): Boolean {
        if (repository.validUsername()) {
            return true
        }
        else {
            return false
        }
        return repository.validUsername()
    }

    fun verifyPassword(): Boolean {
        if (repository.validPassword()) {
            return true
        }
        else {
            return false
        }
        return repository.validPassword()
    }

    fun verifyLogin(): Boolean {
        return verifyName() && verifyPassword()
    }
}