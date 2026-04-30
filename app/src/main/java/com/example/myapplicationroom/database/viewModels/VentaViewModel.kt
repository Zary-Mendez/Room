package com.example.myapplicationroom.database.viewModels

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplicationroom.database.config.TiendaDatabase
import com.example.myapplicationroom.database.entities.Venta
import com.example.myapplicationroom.database.repositories.ArticuloRepository
import com.example.myapplicationroom.database.repositories.VentaRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class VentaViewModel(context: Context) : ViewModel() {
    private val ventaRepository: VentaRepository
    private val articuloRepository: ArticuloRepository

    init {
        val db = TiendaDatabase.getInstance(context)
        this.ventaRepository = VentaRepository(ventaDao = db.ventaDao())
        this.articuloRepository = ArticuloRepository(articuloDao = db.articuloDao())
    }

    fun getAllVentas(load: (ventas: List<Venta>) -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            ventaRepository.getAllVentas().collect {
                load(it)
            }
        }
    }

    fun getVentasByGrupo(grupo: Int, load: (ventas: List<Venta>) -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            ventaRepository.getVentasByGrupo(grupo).collect {
                load(it)
            }
        }
    }

    fun saveNewVenta(grupo: Int, idArticulo: Int, cantidad: Int, success: (state: Boolean) -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            val articulo = articuloRepository.getArticuloById(idArticulo)
            if (articulo != null) {
                val valor = articulo.precio_unitario * cantidad
                ventaRepository.saveNewVenta(
                    Venta(
                        grupo = grupo,
                        id_articulo = idArticulo,
                        cantidad = cantidad,
                        valor = valor
                    )
                )
                success(true)
            } else {
                success(false)
            }
        }
    }

    fun deleteVenta(data: Venta, success: (state: Boolean) -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            ventaRepository.deleteVenta(data)
            success(true)
        }
    }

    fun deleteVentasByGrupo(grupo: Int, success: (state: Boolean) -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            ventaRepository.deleteVentasByGrupo(grupo)
            success(true)
        }
    }
}