package com.example.ucpduaa.repository

import com.example.ucpduaa.data.dao.BarangDao
import com.example.ucpduaa.data.entity.Barang
import kotlinx.coroutines.flow.Flow

class LocalRepositoryBarang(
    private val barangDao: BarangDao
):RepositoryBarang {
    override suspend fun insertbarang(barang: Barang) {
        barangDao.insertbarang(barang)
    }

    override fun getAllBarang(): Flow<List<Barang>> {
        return barangDao.getAllBarang()
    }

    override fun getBarang(id: Int): Flow<Barang> {
        return barangDao.getBarang(id)
    }

    override suspend fun deleteBarang(barang: Barang) {
        barangDao.deleteBarang(barang)
    }

    override suspend fun updateBarang(barang: Barang) {
        barangDao.updateBarang(barang)
    }
}