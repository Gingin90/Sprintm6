package com.example.sprintm6.data

import android.util.Log
import androidx.lifecycle.LiveData

import com.example.sprintm6.data.remote.CellApi
import com.example.sprintm6.data.local.TelefonoDao
import com.example.sprintm6.data.local.TelefonoDetalleEntity
import com.example.sprintm6.data.local.TelefonoEntity

class Repositorio(private val cellApi: CellApi, private val telefonoDao: TelefonoDao) {

    fun obtenerCelulares(): LiveData<List<TelefonoEntity>> = telefonoDao.getTelefonos()

    fun obtenerDetalleCelular(id: Int): LiveData<TelefonoDetalleEntity> = telefonoDao.getCelularDetalle(id)

    suspend fun cargarTelefonos(){
        try {
            val response = cellApi.getDataCell()
            if (response.isSuccessful) {
                val resp = response.body()
                resp?.let { celu ->
                    val telefonoEntity = celu.map {it.transformar() }
                    telefonoDao.insertTelefono(telefonoEntity)
                }
            } else {
                Log.e("repositorio", response.errorBody().toString())
            }
        }catch (exception: Exception){
            Log.e("catch","")
        }
    }


    suspend fun cargarDetalleTelefono(id : Int){
        try {
            val response = cellApi.getDetalleCell(id)
            if (response.isSuccessful) {
                val resp = response.body()
                resp?.let { celu ->
                    val telefonoDetalleEntity = celu.toDetalleEntity()
                    telefonoDao.insertDetalleTelefono(telefonoDetalleEntity)
                }
            } else {
                Log.e("repositorio", response.errorBody().toString())
            }
        }catch (exception: Exception){
            Log.e("catch","")
        }
    }


}