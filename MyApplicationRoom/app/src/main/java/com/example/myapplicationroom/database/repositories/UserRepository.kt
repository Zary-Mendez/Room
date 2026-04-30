package com.example.myapplicationroom.database.repositories

import kotlinx.coroutines.flow.Flow

class UserRepository(val userDao: UserDao) {

    fun getUsers(): Flow<List<User>>{
        return this.userDao.getAllUsers()
    }

    fun saveUsaer(data:User): Unit {
        this.userDao.saveUser(data)
    }
}