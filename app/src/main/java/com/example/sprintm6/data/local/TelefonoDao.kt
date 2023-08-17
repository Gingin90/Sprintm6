package com.example.sprintm6.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface TelefonoDao {

    //Insertar y pedir los datos de Telefono -> base local
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTelefono(telefonoEntity: List<TelefonoEntity>)

    @Query("Select * from tabla_telefonos order by id ASC")
    fun getTelefonos(): LiveData<List<TelefonoEntity>>

    //Insertar y pedir datos de Detalle


    @Insert(onConflict = OnConflictStrategy.REPLACE )
    suspend fun insertDetalleTelefono(telefonoDetalleEntity: TelefonoDetalleEntity)

    @Query("Select * from tabla_celulares_detalle where id = :id")
    fun getCelularDetalle(id: Int): LiveData<TelefonoDetalleEntity>


}