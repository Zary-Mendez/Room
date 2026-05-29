package com.example.myapplicationroom.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.myapplicationroom.database.entities.Articulo
import kotlinx.coroutines.flow.Flow

@Dao
interface ArticuloDao {

    @Query("SELECT * FROM articulos")
    fun getAllArticulos(): Flow<List<Articulo>>

    @Query("SELECT * FROM articulos WHERE id = :id")
    fun getArticuloById(id: Int): Articulo?

    @Insert(onConflict = OnConflictStrategy.NONE)
    fun saveNewArticulo(vararg articulos: Articulo)

    @Update
    fun updateArticulo(vararg articulos: Articulo)

    @Delete
    fun deleteArticulo(articulo: Articulo)
}