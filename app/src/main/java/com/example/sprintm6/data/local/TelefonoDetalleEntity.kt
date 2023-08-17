package com.example.sprintm6.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tabla_celulares_detalle")
data class TelefonoDetalleEntity (
    @PrimaryKey val id: Int,
    val name: String,
    val price: Int,
    val image: String?,
    val description:String,
    val lastPrice:Int,
    val credit: Boolean
)