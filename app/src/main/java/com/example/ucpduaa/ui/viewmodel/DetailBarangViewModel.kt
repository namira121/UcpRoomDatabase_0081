package com.example.ucpduaa.ui.viewmodel

import com.example.ucpduaa.data.entity.Barang

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
