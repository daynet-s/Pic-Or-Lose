package com.sefmat.picorlose.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sefmat.picorlose.data.model.UserModel
import com.sefmat.picorlose.repository.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

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