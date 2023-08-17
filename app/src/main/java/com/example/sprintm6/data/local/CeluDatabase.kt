package com.example.Sprintm6.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.ind8m6.data.local.CelDetalleEntity
import com.example.ind8m6.data.local.CeluEntity

@Database(entities = [CeluEntity::class, CelDetalleEntity::class], version = 1)
abstract class CeluDatabase: RoomDatabase() {

   abstract fun getCeluDao(): CeluDao
     companion object {
         @Volatile
         private var INSTANCE: CeluDatabase? = null


         fun getDatabase(context: Context): CeluDatabase {
             val tempInstance = INSTANCE
             if (tempInstance != null) {
                 return tempInstance
             }
             synchronized(this) {
                 val instance = Room.databaseBuilder(
                     context.applicationContext,
                     CeluDatabase::class.java,
                     "celu_database"
                 ).build()

                 INSTANCE = instance
                 return instance
             }
         }
     }}