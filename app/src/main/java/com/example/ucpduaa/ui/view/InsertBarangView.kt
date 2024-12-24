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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.runtime.traceEventEnd
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ucpduaa.data.ListNamaSpl
import com.example.ucpduaa.ui.customwidget.DropDownSpl
import com.example.ucpduaa.ui.customwidget.TopAppBar
import com.example.ucpduaa.ui.navigation.AlamatNavigasi
import com.example.ucpduaa.ui.viewmodel.BarangEvent
import com.example.ucpduaa.ui.viewmodel.BrgUIState
import com.example.ucpduaa.ui.viewmodel.FormErrorState
import com.example.ucpduaa.ui.viewmodel.InsertBarangViewModel
import com.example.ucpduaa.ui.viewmodel.PenyediaViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.selects.select

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

    LaunchedEffect(uiState.snackBarMessage) {
        uiState.snackBarMessage?.let { message ->
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
                judul = "Tambah Barang",
                modifier = modifier
            )
            InsertBodyBrg(
                uiState=uiState,
                onValueChange = {updatedEvent ->
                    viewModel.updateState(updatedEvent)
                },
                onClick = {
                    viewModel.saveData()
                    onNavigate
                }
            )
        }
    }
}

@Composable
fun InsertBodyBrg(
    modifier: Modifier = Modifier,
    onValueChange: (BarangEvent) -> Unit,
    uiState: BrgUIState,
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        FormBarang(
            barangEvent = uiState.barangEvent,
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
fun FormBarang(
    barangEvent: BarangEvent =BarangEvent(),
    onValueChange: (BarangEvent) -> Unit = {},
    errorState: FormErrorState = FormErrorState(),
    modifier: Modifier = Modifier
){
    var NamaSpl by remember{ mutableStateOf("") }
    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = barangEvent.nama,
            onValueChange = {
                onValueChange(barangEvent.copy(nama = it))
            },
            label = { Text("nama") },
            isError = errorState.nama != null,
            placeholder = { Text("Masukkan nama") }
        )
        Text(text = errorState.nama ?: "",
            color = Color.Red)
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = barangEvent.deskripsi,
            onValueChange = {
                onValueChange(barangEvent.copy(deskripsi = it))
            },
            label = { Text("deskripsi") },
            isError = errorState.deskripsi != null,
            placeholder = { Text("Masukkan deskripsi") }
        )
        Text(text = errorState.deskripsi ?: "",
            color = Color.Red)
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = barangEvent.harga,
            onValueChange = {
                onValueChange(barangEvent.copy(harga = it))
            },
            label = { Text("harga") },
            isError = errorState.harga != null,
            placeholder = { Text("Masukkan harga") }
        )
        Text(text = errorState.harga ?: "",
            color = Color.Red)
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = barangEvent.stok,
            onValueChange = {
                onValueChange(barangEvent.copy(stok = it))
            },
            label = { Text("stok") },
            isError = errorState.stok != null,
            placeholder = { Text("Masukkan stok") }
        )
        Text(text = errorState.stok ?: "",
            color = Color.Red)
        Spacer(modifier = Modifier.height(16.dp))
        DropDownSpl(
            selectedValue = NamaSpl,
            options = ListNamaSpl.DataNamaSpl(),
            label = "Nama Suplier",
            onValueChangedEvent = { selected ->
                NamaSpl = selected
                onValueChange(barangEvent.copy(namaspl = selected))
            }
        )
        Text(
            text = errorState.namaspl ?: "",
            color = Color.Red
        )
        Spacer(modifier = Modifier.height(16.dp))


    }
}