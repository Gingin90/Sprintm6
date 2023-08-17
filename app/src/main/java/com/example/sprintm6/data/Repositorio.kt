package com.example.Sprintm6.data

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.Sprintm6.data.local.CelDetalleEntity
import com.example.Sprintm6.data.local.CeluDao
import com.example.Sprintm6.data.local.CeluEntity
import com.example.Sprintm6.data.remote.CeluAPI
import com.example.ind8m6.data.toEntity


class Repositorio(private val celuAPI: CeluAPI, private val celuDao: CeluDao) {

    fun obtenerCeluEntetity(): LiveData<List<CeluEntity>> = celuDao.getCelu()

    fun obtenerDetalleEntity(id: String): LiveData<List<CelDetalleEntity>> =
        celuDao.getCeluDetalle(id)

    suspend fun getCelu() {

        val response = celuAPI.getDataCell()
        if (response.isSuccessful) {

                val resp = response.body()
                resp?.let { celu ->
                    val CeluEntity= celu.map{it.transformar() }
                    CeluDao.insert(CeluEntity)
                try {


                } catch (exception: Exception) {
                    Log.e("catch", "")
                }

            }
        } else {
            Log.e("repositorio", response.errorBody().toString())
        }

    }

    suspend fun getDetalleCel(id: Long) {

        val response = celuAPI.getDetalleCell(id)
        if (response.isSuccessful) {
            response.body()!!.message.forEach {url->
                val celDetalleEntity = url.toEntity()
                celuDao.insertDetalleCelu(celDetalleEntity )
            }

        } else {
            Log.e("repositorio", response.errorBody().toString())
        }
    }
}




