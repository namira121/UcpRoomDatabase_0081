package com.example.ucpduaa.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ucpduaa.data.entity.Barang
import com.example.ucpduaa.repository.RepositoryBarang
import kotlinx.coroutines.launch
import java.text.Normalizer.Form

data class BarangEvent(
    val id: Int = 0,
    val nama: String = "",
    val deskripsi: String = "",
    val harga: String = "",
    val stok: String = "",
    val namaspl: String = "",
)

fun BarangEvent.toBarangEntity(): Barang = Barang(
    id = id,
    nama = nama,
    deskripsi = deskripsi,
    harga = harga,
    stok = stok,
    namaspl = namaspl
)

data class FormErrorState(
    val id: Int? = null,
    val nama: String? = null,
    val deskripsi: String? = null,
    val harga: String? = null,
    val stok: String? = null,
    val namaspl: String? = null,
) {
    fun isValid(): Boolean {
        return id == null && nama == null && deskripsi == null &&
                harga == null && stok == null && namaspl == null
    }
}

data class BrgUIState(
    val barangEvent: BarangEvent = BarangEvent(),
    val isEntryValid: FormErrorState = FormErrorState(),
    val snackBarMessage: String? = null,
)

class InsertBarangViewModel(private val repositoryBarang: RepositoryBarang) : ViewModel() {

    var uiState by mutableStateOf(BrgUIState())

    fun updateState(BarangEvent: BarangEvent) {
        uiState = uiState.copy(
            barangEvent = BarangEvent,
        )
    }

    private fun validateFields(): Boolean {
        val event = uiState.barangEvent
        val errorState = FormErrorState(
            nama = if (event.nama.isNotEmpty()) null else "Nama tidak boleh kosong",
            deskripsi = if (event.deskripsi.isNotEmpty()) null else "Deskripsi tidak boleh kosong",
            harga = if (event.harga.isNotEmpty()) null else "Harga tidak boleh kosong",
            stok = if (event.stok.isNotEmpty()) null else "Stok tidak boleh kosong",
            namaspl = if (event.namaspl.isNotEmpty()) null else "Nama Suplier tidak boleh kosong",
        )
        uiState = uiState.copy(isEntryValid = errorState)
        return errorState.isValid()
    }

    fun saveData(){
        val currentEvent = uiState.barangEvent

        if(validateFields()){
            viewModelScope.launch {
                try{
                    repositoryBarang.insertbarang(currentEvent.toBarangEntity())
                    uiState = uiState.copy(
                        snackBarMessage = "Data berhasil disimpan",
                        barangEvent = BarangEvent(),
                        isEntryValid = FormErrorState()
                    )
                }catch (e: Exception){
                    uiState = uiState.copy(
                        snackBarMessage = "Data gagal disimpan"
                    )
                }
            }
        }else{
            uiState = uiState.copy(
                snackBarMessage = "Input Tidak Valid"
            )
        }
    }
    fun resetSnackBarMessage(){
        uiState = uiState.copy(snackBarMessage = null)
    }
}





