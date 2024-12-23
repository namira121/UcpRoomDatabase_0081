package com.example.ucpduaa.ui.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.ucpduaa.data.entity.Barang
import com.example.ucpduaa.repository.RepositoryBarang
import com.example.ucpduaa.ui.navigation.DestinasiDetailBarang
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map

fun Barang.toDetailEvent(): BarangEvent{
    return BarangEvent(
        id = id,
        nama = nama,
        deskripsi = deskripsi,
        harga = harga,
        stok = stok,
        namaspl = namaspl
    )
}

data class DetailBrgUIState(
    val detailUiEvent: BarangEvent = BarangEvent(),
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String = ""
){
    val isUiEventEmpty: Boolean
        get() = detailUiEvent == BarangEvent()

    val isUiEventNotEmpty: Boolean
        get() = detailUiEvent != BarangEvent()
}

class DetailBarangViewModel (
    savedStateHandle: SavedStateHandle,
    private val repositoryBarang: RepositoryBarang,
): ViewModel(){
    private val _id: String = checkNotNull(savedStateHandle[DestinasiDetailBarang.ID])

    val detailbrgUIState: StateFlow<DetailBrgUIState> = repositoryBarang.getBarang(_id)
        .filterNotNull()
        .map {
            DetailBrgUIState(
                detailUiEvent = it.toDetailEvent(),
                isLoading = false,
            )
        }
}
