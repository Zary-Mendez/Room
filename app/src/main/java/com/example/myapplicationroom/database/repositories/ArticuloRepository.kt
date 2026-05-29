package com.example.myapplicationroom.database.repositories

import com.example.myapplicationroom.database.dao.ArticuloDao
import com.example.myapplicationroom.database.entities.Articulo
import kotlinx.coroutines.flow.Flow

class ArticuloRepository(val articuloDao: ArticuloDao) {

    fun getArticulos(): Flow<List<Articulo>> {
        return this.articuloDao.getAllArticulos()
    }

    fun getArticuloById(id: Int): Articulo? {
        return this.articuloDao.getArticuloById(id)
    }

    fun saveNewArticulo(data: Articulo): Unit {
        this.articuloDao.saveNewArticulo(data)
    }

    fun updateArticulo(data: Articulo): Unit {
        this.articuloDao.updateArticulo(data)
    }

    fun deleteArticulo(data: Articulo): Unit {
        this.articuloDao.deleteArticulo(data)
    }
}