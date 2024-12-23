package com.example.ucpduaa.repository

import com.example.ucpduaa.data.entity.Barang
import kotlinx.coroutines.flow.Flow

interface RepositoryBarang {
    suspend fun insertbarang(barang: Barang)

    fun getAllBarang(): Flow<List<Barang>>

    fun getBarang(id: String): Flow<Barang>

    suspend fun deleteBarang(barang: Barang)

    suspend fun updateBarang(barang: Barang)
}