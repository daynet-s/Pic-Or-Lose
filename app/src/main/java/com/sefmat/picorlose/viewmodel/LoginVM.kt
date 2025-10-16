package com.sefmat.picorlose.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.sefmat.picorlose.model.LoginModel
import com.sefmat.picorlose.repository.LoginRepository

class LoginViewModel : ViewModel() {
    private val repository = LoginRepository()

    var login: LoginModel by mutableStateOf(repository.getLogin())

    fun verifyName(): Boolean {
        return false // WIP
    }

    fun verifyPassword(): Boolean {
        return false // WIP
    }

    fun verifyLogin(): Boolean {
        return false // WIP
    }
}