package com.example.ucpduaa.data

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ucpduaa.ui.viewmodel.ListSuplierViewModel
import com.example.ucpduaa.ui.viewmodel.PenyediaViewModel

object ListNamaSpl {
    @Composable
    fun ListNamaSpl(
        NamaSplviewModel: ListSuplierViewModel = viewModel(factory = PenyediaViewModel.Factory)
    ): List<String> {
        val getNamaSuplier by NamaSplviewModel.listsplUIState.collectAsState()
        val NamaSuplier = getNamaSuplier.listSpl.map { it.namaspl }
        return NamaSuplier
    }
}