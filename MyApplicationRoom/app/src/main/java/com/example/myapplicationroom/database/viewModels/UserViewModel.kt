package com.example.myapplicationroom.database.viewModels

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplicationroom.database.config.ConexionDB
import com.example.myapplicationroom.database.repositories.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserViewModel(context: Context): ViewModel() {
    private val userRepository: UserRepository

    init {
        val dao = ConexionDB.getDb(context).userDao()
        this.userRepository = UserRepository(userDao = dao)
    }

    fun queryUsers(load: (users:List<User>)-> Unit): Unit{
        viewModelScope.launch(Dispatchers.IO) {
           userRepository.getUsers().collect {
               load(it)
           }
        }
    }

    fun saveUser(user:User, success: (state: Boolean)->Unit): Unit{
        viewModelScope.launch(Dispatchers.IO) {
            userRepository.saveUsaer(user)
            success(true)
        }
    }
}