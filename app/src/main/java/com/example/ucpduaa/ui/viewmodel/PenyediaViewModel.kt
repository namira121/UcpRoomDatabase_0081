package com.example.ucpduaa.ui.viewmodel

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.ucpduaa.TokoApp

object PenyediaViewModel{
    val Factory = viewModelFactory {
        initializer {
            ListBarangViewModel(
                TokoApp().ContainerApp.repositoryBarang
            )
        }
    }
}