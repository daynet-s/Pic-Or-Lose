package com.sefmat.picorlose.repository

import android.util.Log
import com.sefmat.picorlose.data.model.UserDao
import com.sefmat.picorlose.data.model.UserModel
import com.sefmat.picorlose.data.model.UserAPI
import com.sefmat.picorlose.data.remote.RfInstance_Users

class UserRepository(private val dao: UserDao) {
    // FUNCIONES BASE DE DATOS
    suspend fun getAll() = dao.getAll()
    suspend fun insert(user: UserModel) = dao.insert(user)
    suspend fun delete(user: UserModel) = dao.delete(user)
    suspend fun update(user: UserModel) = dao.update(user)
    suspend fun delAll() = dao.delAll()
}

class UserRepository_API {
    // FUNCION API
    suspend fun getUsers(): List<UserAPI> {
        return RfInstance_Users.api.getUser()
    }

    suspend fun insertUser(user: UserAPI): String {
        val response = RfInstance_Users.api.insertUser(user)

        Log.d("UserRepository_API", "Respuesta de la API: ${response.body()}")
        Log.d("UserRepository_API", "URL completa de la solicitud: ${RfInstance_Users.api}")

        if (response.isSuccessful) {
            Log.d("UserRepository_API", "Usuario insertado exitosamente: ${response.body()}")
            return "Usuario insertado exitosamente"
        } else {
            Log.e("UserRepository_API", "Error al insertar usuario: ${response.message()}")
            return "Error al insertar usuario: ${response.message()}"
        }
    }

}