package com.example.ucpduaa.ui.viewmodel

import com.example.ucpduaa.data.entity.Barang

data class BarangEvent(
    val nama: String = "",
    val deskripsi: String = "",
    val harga: String = "",
    val stok: String = "",
    val namaspl: String = "",
)

fun BarangEvent.toBarangEntity(): Barang = Barang(
    nama= nama,
    deskripsi = deskripsi,
    harga = harga,
    stok = stok,
    namspl = namaspl
)
