package com.example.sprintm6.data.remote

import com.example.sprintm6.data.remote.CellDetalle
import com.example.sprintm6.data.remote.Cell
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface CellApi {

    @GET("products/")
    suspend fun getDataCell(): Response<List<Cell>>

    @GET("details/{id}")
    suspend fun getDetalleCell(@Path("id")id:Int): Response<CellDetalle>

}