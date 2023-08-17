package com.example.Sprintm6.presentation
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.Sprintm6.data.Repositorio
import com.example.Sprintm6.data.local.CeluDatabase

import com.example.ind8m6.data.remote.CeluRetrofit

import kotlinx.coroutines.launch


class CelViewModel(application: Application) : AndroidViewModel(application) {
    private val repositorio: Repositorio

    fun celuLiveData() = repositorio.obtenerCeluEntetity()
    fun detalleLiveData(id: String) = repositorio.obtenerDetalleEntity(id)

    init {
        val api = CeluRetrofit.getRetroFitCel()
        val razaDatabase = CeluDatabase.getDatabase(application).getCeluDao()
        repositorio = Repositorio(api,CeluDatabase)
    }


    fun getAllCellu() = viewModelScope.launch {
        repositorio.getCelu()


    }

    fun getDetalle(id: String) = viewModelScope.launch {
        repositorio.getdeta(id)
    }
}