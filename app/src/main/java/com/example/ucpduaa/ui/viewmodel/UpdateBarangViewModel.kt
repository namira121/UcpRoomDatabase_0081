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

    fun UpdateState(barangEvent: BarangEvent){
        updateUIState =updateUIState.copy(
            barangEvent = barangEvent,
        )
    }

    fun ValidateFields(): Boolean{
        val event = updateUIState.barangEvent
        val errorState = FormErrorState(
            nama = if (event.nama.isNotEmpty()) null else "Nama tidak boleh kosong",
            deskripsi = if (event.deskripsi.isNotEmpty()) null else "Deskripsi tidak boleh kosong",
            harga = if (event.harga.isNotEmpty()) null else "Harga tidak boleh kosong",
            stok = if (event.stok.isNotEmpty()) null else "Stok tidak boleh kosong",
            namaspl = if (event.namaspl.isNotEmpty()) null else "Nama Suplier tidak boleh kosong",
        )
        updateUIState =updateUIState.copy(isEntryValid = errorState)
        return errorState.isValid()
    }

    fun updateData(){
        val currentEvent = updateUIState.barangEvent

        if(ValidateFields()){
            viewModelScope.launch {
                try {
                    repositoryBarang.updateBarang(currentEvent.toBarangEntity())
                    updateUIState = updateUIState.copy(
                        snackBarMessage = "Data berhasil diupdate",
                        barangEvent = BarangEvent(),
                        isEntryValid = FormErrorState()
                    )
                    println("snackBarMessage diatur: ${updateUIState.snackBarMessage}")
                }catch (e: Exception){
                    updateUIState = updateUIState.copy(
                        snackBarMessage = "Data gagal diupdate"
                    )
                }
            }
        } else{
            updateUIState = updateUIState.copy(
                snackBarMessage = "Data gagal diupdate"
            )
        }
    }

    fun resetSnackBarMessage(){
        updateUIState = updateUIState.copy(snackBarMessage = null)
    }
}