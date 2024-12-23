package com.example.ucpduaa.ui.viewmodel

import com.example.ucpduaa.data.entity.Barang
import com.example.ucpduaa.data.entity.Suplier

data class ListSplUIState(
    val listSpl: List<Suplier> = listOf(),
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String = ""
)