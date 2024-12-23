package com.example.ucpduaa.repository

import com.example.ucpduaa.data.entity.Suplier
import kotlinx.coroutines.flow.Flow

interface RepositorySuplier {
    suspend fun insertsuplier(suplier: Suplier)

    fun getAllSuplier(): Flow<List<Suplier>>
}