package com.example.ucpduaa.data.dao

import androidx.room.Dao
import androidx.room.Insert
import com.example.ucpduaa.data.entity.Barang

@Dao
interface BarangDao {
    @Insert
    suspend fun insertbarang(
        barang: Barang
    )
}