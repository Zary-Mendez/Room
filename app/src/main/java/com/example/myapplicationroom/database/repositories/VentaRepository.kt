package com.example.myapplicationroom.database.repositories

import com.example.myapplicationroom.database.dao.VentaDao
import com.example.myapplicationroom.database.entities.Venta
import kotlinx.coroutines.flow.Flow

class VentaRepository(val ventaDao: VentaDao) {

    fun getAllVentas(): Flow<List<Venta>> {
        return this.ventaDao.getAllVentas()
    }

    fun getVentasByGrupo(grupo: Int): Flow<List<Venta>> {
        return this.ventaDao.getVentasByGrupo(grupo)
    }

    fun getUltimoGrupo(): Int? {
        return this.ventaDao.getUltimoGrupo()
    }

    fun saveNewVenta(data: Venta): Unit {
        this.ventaDao.saveNewVenta(data)
    }

    fun updateVenta(data: Venta): Unit {
        this.ventaDao.updateVenta(data)
    }

    fun deleteVenta(data: Venta): Unit {
        this.ventaDao.deleteVenta(data)
    }

    fun deleteVentasByGrupo(grupo: Int): Unit {
        this.ventaDao.deleteVentasByGrupo(grupo)
    }
}