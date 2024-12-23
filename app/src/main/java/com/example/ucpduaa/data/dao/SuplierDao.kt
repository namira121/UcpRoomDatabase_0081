package com.example.ucpduaa.data.dao

import androidx.room.Dao
import androidx.room.Insert
import com.example.ucpduaa.data.entity.Suplier

@Dao
interface SuplierDao {
    @Insert
    suspend fun insertsuplier(
        suplier: Suplier
    )
}