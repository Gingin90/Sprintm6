package com.example.Sprintm6.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tabla_celulares_detalle")
    data class CelDetalleEntity(
   @PrimaryKey val id: Long,
    val name: String,
    val price: Long,
    val image: String,
    val description: String,
    val lastPrice: Long,
    val credit: Boolean)



