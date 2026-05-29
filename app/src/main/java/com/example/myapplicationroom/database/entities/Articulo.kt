package com.example.myapplicationroom.database.entities  // ← corregir esto

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "articulos")
data class Articulo(
    @PrimaryKey val id: Int,
    val nombre: String,
    val descripcion: String,
    val precio_unitario: Int
)