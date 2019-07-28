package com.hexi.kotlindemo.persistence

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Flowable

@Dao
interface UserDao {

    @Query("SELECT * FROM users")
    fun getAllUsers(): Flowable<List<User>>

    @Query("SELECT * FROM users WHERE userid = :id")
    fun getUserById(id: String): Flowable<User>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(user: User): Long

    @Query("DELETE FROM users")
    fun deleteAllUsers(): Int
}