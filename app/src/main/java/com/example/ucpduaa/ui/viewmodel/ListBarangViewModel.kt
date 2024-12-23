package com.example.ucpduaa.ui.viewmodel

import com.example.ucpduaa.data.entity.Barang

data class ListBrgUIState(
    val listBrg: List<Barang> = listOf(),
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String = ""
)