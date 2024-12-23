package com.example.ucpduaa.ui.viewmodel

import com.example.ucpduaa.data.entity.Barang

fun Barang.toUIStateBrg():BrgUIState = BrgUIState(
    barangEvent = this.toDetailEvent(),
)