package com.example.ucpduaa.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.ucpduaa.data.entity.Barang
import com.example.ucpduaa.repository.RepositoryBarang
import com.example.ucpduaa.ui.navigation.DestinasiUpdateBarang

fun Barang.toUIStateBrg():BrgUIState = BrgUIState(
    barangEvent = this.toDetailEvent(),
)

class UpdateBarangViewModel(
    savedStateHandle: SavedStateHandle,
    private val repositoryBarang: RepositoryBarang
):ViewModel(){
    var updateUIState by mutableStateOf(BrgUIState())
        private set

    private val _id: Int = checkNotNull(savedStateHandle[DestinasiUpdateBarang.ID])

}