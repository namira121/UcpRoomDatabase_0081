package com.example.ucpduaa.ui.view

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ucpduaa.ui.navigation.AlamatNavigasi
import com.example.ucpduaa.ui.viewmodel.InsertBarangViewModel
import com.example.ucpduaa.ui.viewmodel.PenyediaViewModel

object DestinasiInsertBrg : AlamatNavigasi{
    override val route: String = "insertbarang"
}

@Composable
fun InsertBarangView(
    onBack: () -> Unit,
    onNavigate: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: InsertBarangViewModel = viewModel(factory = PenyediaViewModel.Factory)
){
    val uiState = viewModel.uiState
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutlineScope = rememberCoroutineScope()
}