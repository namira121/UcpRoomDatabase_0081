package com.example.ucpduaa.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.ucpduaa.data.entity.Barang
import com.example.ucpduaa.repository.RepositoryBarang
import java.text.Normalizer.Form

class InsertBarangViewModel(private val repositoryBarang: RepositoryBarang) : ViewModel() {

    var uiState by mutableStateOf(BrgUIState())

    fun updateState(BarangEvent: BarangEvent){
        uiState = uiState.copy(
            barangEvent = BarangEvent,
        )
    }

    data class BarangEvent(
        val id: Int,
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
}


