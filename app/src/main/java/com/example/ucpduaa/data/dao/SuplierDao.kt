package com.example.ucpduaa.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.ucpduaa.data.entity.Suplier
import kotlinx.coroutines.flow.Flow

@Dao
interface SuplierDao {
    @Insert
    suspend fun insertsuplier(
        suplier: Suplier
    )
    @Query("SELECT * FROM suplier ORDER BY namaspl ASC")
    fun getAllSuplier(): Flow<List<Suplier>>
}