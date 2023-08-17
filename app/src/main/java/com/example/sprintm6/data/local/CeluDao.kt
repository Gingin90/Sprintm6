package com.example.Sprintm6.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.ind8m6.data.local.CelDetalleEntity




@Dao
interface CeluDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCelu(celuEntity: CeluEntity)

    //para pruebas android
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCelu(celuEntity: List<CeluEntity>)

    @Query(" Select * from tabla_celu order by celu desc ")
    fun getCelu(): LiveData<List<CeluEntity>>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDetalleCelu(celuDetalleEntity: CelDetalleEntity)

    @Query("Select * from tabla_celudetalle where celdetalle like :id")
    fun getCeluDetalle(id: String): LiveData<List<CelDetalleEntity>>
}