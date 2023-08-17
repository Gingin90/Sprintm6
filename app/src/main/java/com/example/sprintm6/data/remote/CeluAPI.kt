package com.example.Sprintm6.data.remote

import com.example.Sprintm6.data.remote.Celus
import com.example.spritm6.data.remote.CelDetalle
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface CeluAPI {

        @GET("breeds/list/all")
        suspend fun getData(): Response<Celus>
    @GET("breed/{id}/images")
    suspend fun getDetalleCel(@Path("id") id: String): Response<CelDetalle>}