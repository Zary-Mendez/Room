package com.example.myapplicationroom.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.myapplicationroom.database.entities.Venta
import kotlinx.coroutines.flow.Flow

@Dao
interface VentaDao {

    @Query("SELECT * FROM ventas")
    fun getAllVentas(): Flow<List<Venta>>

    @Query("SELECT * FROM ventas WHERE grupo = :grupo")
    fun getVentasByGrupo(grupo: Int): Flow<List<Venta>>

    @Query("SELECT MAX(grupo) FROM ventas")
    fun getUltimoGrupo(): Int?

    @Insert(onConflict = OnConflictStrategy.NONE)
    fun saveNewVenta(vararg ventas: Venta)

    @Update
    fun updateVenta(vararg ventas: Venta)

    @Delete
    fun deleteVenta(venta: Venta)

    @Query("DELETE FROM ventas WHERE grupo = :grupo")
    fun deleteVentasByGrupo(grupo: Int)
}