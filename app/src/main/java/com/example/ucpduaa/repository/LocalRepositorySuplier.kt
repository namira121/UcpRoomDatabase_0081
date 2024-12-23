package com.example.ucpduaa.repository

import com.example.ucpduaa.data.dao.SuplierDao
import com.example.ucpduaa.data.entity.Suplier

class LocalRepositorySuplier(
    private val suplierDao: SuplierDao
): RepositorySuplier {
    override suspend fun insertsuplier(suplier: Suplier) {
        suplierDao.insertsuplier(suplier)
    }
}