package com.sefmat.picorlose.data.model

import androidx.room.*

@Dao
interface UserDao {
    @Query("SELECT * FROM users")
    suspend fun getAll(): List<UserModel>

    @Insert
    suspend fun insert(user: UserModel)

    @Update
    suspend fun update(user: UserModel)

    @Delete
    suspend fun delete(user: UserModel)

    @Query("DELETE FROM users")
    suspend fun delAll()
}