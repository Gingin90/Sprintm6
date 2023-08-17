package com.example.ind8m6.data

import com.example.ind8m6.data.local.CelDetalleEntity
import com.example.ind8m6.data.local.CeluEntity

fun String.toEntity(id:String): CelDetalleEntity = CelDetalleEntity(id,this)

fun String.toRazaEntity(): CeluEntity = CeluEntity(this)