package com.example.Sprintm6.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "tabla_celu")
data class CeluEntity(@PrimaryKey val id :String, val price:Int, val type:String, val img:String) {

}

