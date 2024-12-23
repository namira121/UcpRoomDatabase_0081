package com.example.ucpduaa.repository

import com.example.ucpduaa.data.dao.BarangDao
import com.example.ucpduaa.data.entity.Barang

class LocalRepositoryBarang(
    private val barangDao: BarangDao
):RepositoryBarang {
    override suspend fun insertbarang(barang: Barang) {
        barangDao.insertbarang(barang)
    }
}