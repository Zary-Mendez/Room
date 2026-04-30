package com.tuapp.database.dao

import androidx.room.*
import com.tuapp.database.entities.Venta
import kotlinx.coroutines.flow.Flow

@Dao
interface VentaDao {

    @Query("SELECT * FROM ventas WHERE grupo = :grupo")
    fun getByGrupo(grupo: Int): Flow<List<Venta>>

    @Query("SELECT * FROM ventas ORDER BY grupo ASC, id ASC")
    fun getAll(): Flow<List<Venta>>

    @Query("SELECT MAX(grupo) FROM ventas")
    suspend fun getUltimoGrupo(): Int?

    @Insert
    suspend fun insert(venta: Venta)

    @Query("DELETE FROM ventas WHERE grupo = :grupo")
    suspend fun deleteByGrupo(grupo: Int)

    @Delete
    suspend fun delete(venta: Venta)
}