package com.example.myapplicationroom.database.entities

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
            onDelete = ForeignKey.RESTRICT
        )
    ],
    indices = [Index("id_articulo")]
)
data class Venta(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val grupo: Int,
    val id_articulo: Int,
    val cantidad: Int,
    val valor: Int
)