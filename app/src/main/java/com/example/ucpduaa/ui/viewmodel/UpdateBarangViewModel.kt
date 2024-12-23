package com.example.ucpduaa.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ucpduaa.data.entity.Barang
import com.example.ucpduaa.repository.RepositoryBarang
import com.example.ucpduaa.ui.navigation.DestinasiUpdateBarang
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

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

    init {
        viewModelScope.launch {
            updateUIState=repositoryBarang.getBarang(_id)
                .filterNotNull()
                .first()
                .toUIStateBrg()
        }
    }
}