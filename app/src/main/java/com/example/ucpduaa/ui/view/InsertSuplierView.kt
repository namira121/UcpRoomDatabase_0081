package com.example.ucpduaa.ui.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ucpduaa.ui.customwidget.TopAppBar
import com.example.ucpduaa.ui.navigation.AlamatNavigasi
import com.example.ucpduaa.ui.viewmodel.BarangEvent
import com.example.ucpduaa.ui.viewmodel.BrgUIState
import com.example.ucpduaa.ui.viewmodel.FormErrorState
import com.example.ucpduaa.ui.viewmodel.FormErrorStateSpl
import com.example.ucpduaa.ui.viewmodel.InsertBarangViewModel
import com.example.ucpduaa.ui.viewmodel.InsertSuplierViewModel
import com.example.ucpduaa.ui.viewmodel.PenyediaViewModel
import com.example.ucpduaa.ui.viewmodel.SplUIState
import com.example.ucpduaa.ui.viewmodel.SuplierEvent
import kotlinx.coroutines.launch

object DestinasiInsertSpl : AlamatNavigasi {
    override val route: String = "insertsuplier"
}

@Composable
fun InsertSuplierView(
    onBack: () -> Unit,
    onNavigate: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: InsertSuplierViewModel = viewModel(factory = PenyediaViewModel.Factory)
){
    val uiStatesuplier = viewModel.SuplieruiState
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutlineScope = rememberCoroutineScope()

    LaunchedEffect(uiStatesuplier.snackBarMessage) {
        uiStatesuplier.snackBarMessage?.let { message ->
            coroutlineScope.launch {
                snackbarHostState.showSnackbar(message)
                viewModel.resetSnackBarMessage()
            }
        }
    }
    Scaffold(
        modifier = modifier,
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
    ) {
            padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
        ) {
            TopAppBar(
                onBack = onBack,
                showBackButton = true,
                judul = "Tambah Suplier",
                modifier = modifier
            )
            InsertBodySpl(
                uiState=uiStatesuplier,
                onValueChange = {updatedEvent ->
                    viewModel.updateSplState(updatedEvent)
                },
                onClick = {
                    viewModel.saveDataSpl()
                    onNavigate
                }
            )
        }
    }
}

@Composable
fun InsertBodySpl(
    modifier: Modifier = Modifier,
    onValueChange: (SuplierEvent) -> Unit,
    uiState: SplUIState,
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        FormSuplier(
            suplierEvent =uiState.suplierEvent,
            onValueChange = onValueChange,
            errorState = uiState.isEntryValid,
            modifier = Modifier.fillMaxWidth()
        )
        Button(
            onClick = onClick,
            modifier= Modifier.fillMaxWidth(),
        ) {
            Text("Simpan")
        }
    }
}

@Composable
fun FormSuplier(
    suplierEvent: SuplierEvent = SuplierEvent(),
    onValueChange: (SuplierEvent) -> Unit = {},
    errorState: FormErrorStateSpl = FormErrorStateSpl(),
    modifier: Modifier = Modifier
){
    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = suplierEvent.namaspl,
            onValueChange = {
                onValueChange(suplierEvent.copy(namaspl = it))
            },
            label = { Text("nama suplier") },
            isError = errorState.namaspl != null,
            placeholder = { Text("Masukkan nama suplier") }
        )
        Text(text = errorState.namaspl ?: "",
            color = Color.Red)
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = suplierEvent.kontakspl,
            onValueChange = {
                onValueChange(suplierEvent.copy(kontakspl = it))
            },
            label = { Text("Kontak Suplier") },
            isError = errorState.kontakspl != null,
            placeholder = { Text("Masukkan Kontak Suplier") }
        )
        Text(text = errorState.kontakspl ?: "",
            color = Color.Red)
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = suplierEvent.alamatspl,
            onValueChange = {
                onValueChange(suplierEvent.copy(alamatspl = it))
            },
            label = { Text("Alamat Suplier") },
            isError = errorState.alamatspl != null,
            placeholder = { Text("Masukkan Alamat Suplier") }
        )
        Text(text = errorState.alamatspl?: "",
            color = Color.Red)

    }
}