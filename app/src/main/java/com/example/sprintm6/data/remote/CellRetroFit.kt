package com.example.sprintm6.data.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CellRetroFit {
    companion object{

        private const val BASE_URL = "https://my-json-server.typicode.com/Himuravidal/FakeAPIdata/"

        fun gedRetroFitCell(): CellApi {

            val mRetrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            return mRetrofit.create(CellApi::class.java)

        }


    }
}