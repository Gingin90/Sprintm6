package com.example.Sprintm6.data

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.Sprintm6.data.local.CeluDao
import com.example.Sprintm6.data.local.CeluEntity
import com.example.Sprintm6.data.remote.CeluAPI
import com.example.ind8m6.data.local.CelDetalleEntity
import com.example.ind8m6.data.toEntity


class Repositorio(private val celuAPI: CeluAPI, private val celuDao: CeluDao) {

    fun obtenerCeluEntetity(): LiveData<List<CeluEntity>> = celuDao.getCelu()

    fun obtenerDetalleEntity(id: String): LiveData<List<CelDetalleEntity>> =
        celuDao.getCeluDetalle(id)

    suspend fun getCelu() {

        val response = celuAPI.getData()
        if (response.isSuccessful) {
            val message = response.body()!!.message
            val keys = message.keys
            keys.forEach {celu->
                val celuEntity = celu.toCeluEntity()
                celuDao.insertCelu(celuEntity)
                try {


                } catch (exception: Exception) {
                    Log.e("catch", "")
                }

            }
        } else {
            Log.e("repositorio", response.errorBody().toString())
        }

    }

    suspend fun getDetallePerro(id: String) {

        val response = celuAPI.getDetalleCel(id)
        if (response.isSuccessful) {
            response.body()!!.message.forEach {url->
                val CelDetalleEntity = url.toEntity(id)
                celuDao.insertDetalleCelu(CelDetalleEntity )
            }

        } else {
            Log.e("repositorio", response.errorBody().toString())
        }
    }
}




