package com.example.ucpduaa.repository

import com.example.ucpduaa.data.entity.Suplier

interface RepositorySuplier {
    suspend fun insertsuplier(suplier: Suplier)

    fun getAllSuplier()
}