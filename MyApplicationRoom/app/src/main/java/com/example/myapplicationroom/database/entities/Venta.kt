package com.tuapp.database.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "ventas",
    foreignKeys = [
        ForeignKey(
            entity = Articulo::class,
            parentColumns = ["id"],
            childColumns = ["id_articulo"],
            onDelete = ForeignKey.RESTRICT   // evita borrar artículo si tiene ventas
        )
    ],
    indices = [Index("id_articulo")]
)
data class Venta(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val grupo: Int,          // número de venta completa, lo pondrá el usuario
    val id_articulo: Int,    // FK a articulos
    val cantidad: Int,
    val valor: Int           // precio_unitario * cantidad, calculado en Kotlin
)