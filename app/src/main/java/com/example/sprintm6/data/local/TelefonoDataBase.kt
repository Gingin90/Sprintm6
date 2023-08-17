package com.example.sprintm6.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [TelefonoEntity::class, TelefonoDetalleEntity::class], version = 1)
abstract class TelefonoDataBase:RoomDatabase() {

    abstract fun getTelefonoDao(): TelefonoDao

    companion object {
        @Volatile
        private var INSTANCE: TelefonoDataBase? = null

        fun getDatabase(context: Context): TelefonoDataBase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    TelefonoDataBase::class.java,
                    "telefono_database"
                ).build()

                INSTANCE = instance
                return instance
            }
        }
    }

}