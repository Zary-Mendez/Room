package com.example.myapplicationroom.database.viewModels

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplicationroom.database.config.TiendaDatabase
import com.example.myapplicationroom.database.entities.Articulo
import com.example.myapplicationroom.database.repositories.ArticuloRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ArticuloViewModel(context: Context) : ViewModel() {
    private val articuloRepository: ArticuloRepository

    init {
        val dao = TiendaDatabase.getInstance(context).articuloDao()
        this.articuloRepository = ArticuloRepository(articuloDao = dao)
    }

    fun getArticulos(load: (articulos: List<Articulo>) -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            articuloRepository.getArticulos().collect {
                load(it)
            }
        }
    }

    fun getArticuloById(id: Int, load: (articulo: Articulo?) -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            val articulo = articuloRepository.getArticuloById(id)
            load(articulo)
        }
    }

    fun saveNewArticulo(data: Articulo, success: (state: Boolean) -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            articuloRepository.saveNewArticulo(data)
            success(true)
        }
    }

    fun updateArticulo(data: Articulo, success: (state: Boolean) -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            articuloRepository.updateArticulo(data)
            success(true)
        }
    }

    fun deleteArticulo(data: Articulo, success: (state: Boolean) -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            articuloRepository.deleteArticulo(data)
            success(true)
        }
    }
}