package com.example.ucpduaa.ui.viewmodel

import com.example.ucpduaa.data.entity.Barang

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
    nama= nama,
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
){
    fun isValid(): Boolean{
        return id == null && nama == null && deskripsi == null &&
                harga == null && stok == null && namaspl == null
    }
}


