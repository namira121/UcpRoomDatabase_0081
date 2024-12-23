package com.example.ucpduaa.dependenciesinjection

import android.content.Context
import com.example.ucpduaa.data.database.TokoDatabase
import com.example.ucpduaa.repository.LocalRepositoryBarang
import com.example.ucpduaa.repository.LocalRepositorySuplier
import com.example.ucpduaa.repository.RepositoryBarang
import com.example.ucpduaa.repository.RepositorySuplier

interface InterfaceContainerApp{
    val repositoryBarang:RepositoryBarang
    val repositorySuplier: RepositorySuplier
}

class ContainerApp(private val context: Context): InterfaceContainerApp{
    override val repositoryBarang: RepositoryBarang by lazy {
        LocalRepositoryBarang(TokoDatabase.getDatabse(context).barangDao())
    }
    override val repositorySuplier: RepositorySuplier by lazy {
        LocalRepositorySuplier(TokoDatabase.getDatabse(context).suplierDao())
    }

}