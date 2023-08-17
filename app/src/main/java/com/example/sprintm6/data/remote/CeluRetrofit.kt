package com.example.ind8m6.data.remote

import com.example.Sprintm6.data.remote.CeluAPI
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class CeluRetrofit {

    companion object {
        private const val URL_BASE ="https://my-json-server.typicode.com/Himuravidal/FakeAPIdata/products/"

        fun getRetroFitCel(): CeluAPI {

            val mRetrofit = Retrofit.Builder()
                .baseUrl(URL_BASE)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            return mRetrofit.create(CeluAPI ::class.java)

        }

    }
}