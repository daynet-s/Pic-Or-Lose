package com.sefmat.picorlose.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sefmat.picorlose.data.model.PictureModel
import com.sefmat.picorlose.data.model.UserAPI
import com.sefmat.picorlose.data.model.UserModel
import com.sefmat.picorlose.repository.PictureRepository
import com.sefmat.picorlose.repository.UserRepository
import com.sefmat.picorlose.repository.UserRepository_API
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

// VIEWMODEL DE USUARIOS DE BASE DE DATOS LOCAL
class UserVM(private val repo: UserRepository) : ViewModel() {
    val username = MutableStateFlow("")
    val password = MutableStateFlow("")
    val points = MutableStateFlow(0)
    //val points = _points.asStateFlow()

    val users = MutableStateFlow<List<UserModel>>(emptyList())

    init {
        loadUsers()
    }

    private fun loadUsers() {
        viewModelScope.launch {
            users.value = repo.getAll()
        }
    }

    fun delAll() {
        viewModelScope.launch {
            repo.delAll()
        }
    }

    fun insertUser(user: UserModel) {
        viewModelScope.launch {
            repo.insert(user)
            loadUsers()
        }
    }

    fun deleteUser(user: UserModel) {
        viewModelScope.launch {
            repo.delete(user)
            loadUsers()
        }
    }

    fun updateUser(user: UserModel) {
        viewModelScope.launch {
            repo.update(user)
            loadUsers()
        }
    }
}

// VIEWMODEL DE USUARIOS API
class UserVM_API : ViewModel() {
    val username = MutableStateFlow("")
    val password = MutableStateFlow("")
    val points = MutableStateFlow(0)
    private val repo = UserRepository_API()
    private val _users = MutableStateFlow<List<UserAPI>>(emptyList())

    val users: StateFlow<List<UserAPI>> = _users

    init {
        fetchUsers()
    }

    private fun fetchUsers() {
        viewModelScope.launch {
            _users.value = repo.getUsers()
        }
    }

    fun insertUser(user: UserAPI) {
        viewModelScope.launch {
            try {
                Log.d("UserVM", "Datos a insertar: $user")

                val response = repo.insertUser(user)
                Log.d("UserVM_API", "Respuesta: ${response}")
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}