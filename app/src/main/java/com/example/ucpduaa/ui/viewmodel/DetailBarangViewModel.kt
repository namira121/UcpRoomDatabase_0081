package com.example.ucpduaa.ui.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ucpduaa.data.entity.Barang
import com.example.ucpduaa.repository.RepositoryBarang
import com.example.ucpduaa.ui.navigation.DestinasiDetailBarang
import com.example.ucpduaa.ui.navigation.DestinasiUpdateBarang
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

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
    private val _id: Int = checkNotNull(savedStateHandle[DestinasiDetailBarang.ID])


    val detailbrgUIState: StateFlow<DetailBrgUIState> = repositoryBarang.getBarang(_id)
        .filterNotNull()
        .map {
            DetailBrgUIState(
                detailUiEvent = it.toDetailEvent(),
                isLoading = false,
            )
        }
        .catch {
            emit(
                DetailBrgUIState(
                    isLoading = false,
                    isError = true,
                    errorMessage = it.message ?:"Tejadi Kesalahan",
                )
            )
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(2000),
            initialValue = DetailBrgUIState(
                isLoading = true,
            )
        )
    fun deleteBrg(){
        detailbrgUIState.value.detailUiEvent.toBarangEntity().let {
            viewModelScope.launch {
                repositoryBarang.deleteBarang(it)
            }
        }
    }
}
