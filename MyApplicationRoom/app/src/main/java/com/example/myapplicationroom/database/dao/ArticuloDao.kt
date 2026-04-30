package com.tuapp.database.dao

import androidx.room.*
import com.tuapp.database.entities.Articulo
import kotlinx.coroutines.flow.Flow

@Dao
interface ArticuloDao {

    @Query("SELECT * FROM articulos ORDER BY id ASC")
    fun getAll(): Flow<List<Articulo>>

    @Query("SELECT * FROM articulos WHERE id = :id")
    suspend fun getById(id: Int): Articulo?

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insert(articulo: Articulo)

    @Update
    suspend fun update(articulo: Articulo)

    @Delete
    suspend fun delete(articulo: Articulo)
}