package com.example.sprintm6.data.remote
/*
{
    "id": 3,
    "name": "Apple iPhone 7 32GB",
    "price": 323760,
    "image": "https://daisycon.io/images/mobile-device/?width=250&height=250&color=ffffff&mobile_device_brand=apple&mobile_device_model=iphone+7+32gb&mobile_device_color=silver",
    "description": "Tamaño 4,7''\nDensidad 326 ppi\nResolución de pantalla 1334 x 750 pixeles",
    "lastPrice": 393760,
    "credit": true
}

 */

data class CellDetalle(
    val id: Int,
    val name: String,
    val price: Int,
    val image: String,
    val description:String,
    val lastPrice:Int,
    val credit: Boolean
)