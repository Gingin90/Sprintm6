package com.example.sprintm6.data.remote

data class CellDetalle(
    val id: Int,
    val name: String,
    val price: Int,
    val image: String,
    val description:String,
    val lastPrice:Int,
    val credit: Boolean
)