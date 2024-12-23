package com.example.ucpduaa.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.ucpduaa.data.entity.Barang
import kotlinx.coroutines.flow.Flow

@Dao
interface BarangDao {
    @Insert
    suspend fun insertbarang(
        barang: Barang
    )

    @Query("SELECT * FROM barang ORDER BY nama ASC")
    fun getAllBarang(): Flow<List<Barang>>

    @Query("SELECT* FROM barang WHERE id = :id")
    fun getBarang(id:String): Flow<Barang>
}