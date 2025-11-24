package com.sefmat.picorlose.repository

import com.sefmat.picorlose.data.model.UserDao
import com.sefmat.picorlose.data.model.UserModel

class UserRepository(private val dao: UserDao) {
    // FUNCIONES BASE DE DATOS
    suspend fun getAll() = dao.getAll()
    suspend fun insert(user: UserModel) = dao.insert(user)
    suspend fun delete(user: UserModel) = dao.delete(user)
    suspend fun update(user: UserModel) = dao.update(user)
    suspend fun delAll() = dao.delAll()
}