package com.example.myapplicationroom.database.config

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.myapplicationroom.database.dao.ArticuloDao
import com.example.myapplicationroom.database.dao.VentaDao
import com.example.myapplicationroom.database.entities.Articulo
import com.example.myapplicationroom.database.entities.Venta

@Database(
    entities = [Articulo::class, Venta::class],
    version = 1,
    exportSchema = false
)
abstract class TiendaDatabase : RoomDatabase() {

    abstract fun articuloDao(): ArticuloDao
    abstract fun ventaDao(): VentaDao

    companion object {
        @Volatile
        private var INSTANCE: TiendaDatabase? = null

        fun getInstance(context: Context): TiendaDatabase {
            return INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    TiendaDatabase::class.java,
                    "tienda_db"
                ).build().also { INSTANCE = it }
            }
        }
    }
}