package com.example.Sprintm6.data.remote


import com.example.sprintm6.data.remote.Celu
import com.example.spritm6.data.remote.CelDetalle
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface CeluAPI {

    @GET("products/")
    suspend fun getDataCell(): Response<List<Celu>>

    @GET("details/{id}")
    suspend fun getDetalleCell(@Path("id") id: Int): Response<CelDetalle>
}
