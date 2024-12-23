package com.example.ucpduaa.repository

import com.example.ucpduaa.data.entity.Barang

interface RepositoryBarang {
    suspend fun insertbarang(barang: Barang)

}