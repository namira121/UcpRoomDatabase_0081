package com.example.ucpduaa.data

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ucpduaa.data.entity.Suplier
import com.example.ucpduaa.ui.viewmodel.ListSuplierViewModel
import com.example.ucpduaa.ui.viewmodel.PenyediaViewModel

object ListNamaSpl {
    @Composable
    fun DataNamaSpl(
        NamaSplviewModel: ListSuplierViewModel = viewModel(factory = PenyediaViewModel.Factory)
    ): List<String> {
        val getNamaSuplier = NamaSplviewModel.listsplUIState.collectAsState()
        val listNamaSuplier = getNamaSuplier.value.listSpl
        val NamaSuplier = listNamaSuplier.map { suplier -> suplier.namaspl  }
        return NamaSuplier
    }
}