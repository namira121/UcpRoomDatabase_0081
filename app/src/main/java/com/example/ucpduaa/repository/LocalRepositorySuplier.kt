package com.example.ucpduaa.repository

import com.example.ucpduaa.data.dao.SuplierDao
import com.example.ucpduaa.data.entity.Suplier
import kotlinx.coroutines.flow.Flow

class LocalRepositorySuplier(
    private val suplierDao: SuplierDao
): RepositorySuplier {
    override suspend fun insertsuplier(suplier: Suplier) {
        suplierDao.insertsuplier(suplier)
    }

    override fun getAllSuplier(): Flow<List<Suplier>> {
        return suplierDao.getAllSuplier()
    }
}