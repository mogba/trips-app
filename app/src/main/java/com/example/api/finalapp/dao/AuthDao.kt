package com.example.api.finalapp.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.api.finalapp.model.User

@Dao
interface AuthDao {
    @Query("""
        select *
        from User
        where
            email = :email and
            password = :password
        limit 1
    """)
    suspend fun login(email: String, password: String): User?

    @Insert
    suspend fun register(user: User)
}