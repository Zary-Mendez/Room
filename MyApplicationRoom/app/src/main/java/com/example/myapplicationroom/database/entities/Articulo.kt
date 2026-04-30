package com.tuapp.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "articulos")
data class Articulo(
    @PrimaryKey val id: Int,          // El usuario escribe ej: 001
    val nombre: String,
    val descripcion: String,
    val precio_unitario: Int
)