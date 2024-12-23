package com.example.ucpduaa.ui.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ucpduaa.data.entity.Barang
import com.example.ucpduaa.ui.customwidget.TopAppBar
import com.example.ucpduaa.ui.viewmodel.ListBarangViewModel
import com.example.ucpduaa.ui.viewmodel.ListBrgUIState
import com.example.ucpduaa.ui.viewmodel.PenyediaViewModel
import kotlinx.coroutines.launch

@Composable
fun CardBarang(
    brg: Barang,
    modifier: Modifier=Modifier,
    onClick: () -> Unit = { }
){
    Card(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)
    ){
        Column(
            modifier = Modifier.padding(8.dp)
        ) {
            Row(modifier=Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically) {
                Icon(imageVector = Icons.Filled.ShoppingCart, contentDescription = "")
                Spacer(modifier = Modifier.padding(4.dp))
                Text(
                    text = brg.nama,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
            }
            Row(modifier=Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically) {
                Icon(imageVector = Icons.Filled.Info, contentDescription = "")
                Spacer(modifier = Modifier.padding(4.dp))
                Text(
                    text = brg.deskripsi,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
            }
            Row(modifier=Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically) {
                Icon(imageVector = Icons.Filled.Star, contentDescription = "")
                Spacer(modifier = Modifier.padding(4.dp))
                Text(
                    text = brg.stok,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
            }
        }
    }
}

@Composable
fun ListBarang(
    listBrg: List<Barang>,
    modifier: Modifier = Modifier,
    onClick: (Int) -> Unit = { }
){
    LazyColumn(
        modifier=modifier
    ) {
        items(
            items = listBrg,
            itemContent = { brg ->
                CardBarang(
                    brg=brg,
                    onClick = {onClick(brg.id)}
                )
            }
        )
    }
}

@Composable
fun BodyListBarangView(
    listbrgUIState: ListBrgUIState,
    onClick: (Int) -> Unit = { },
    modifier: Modifier = Modifier
){
    val coroutineScope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }
    when{
        listbrgUIState.isLoading -> {
            Box(
                modifier=modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ){
                CircularProgressIndicator()
            }
        }
        listbrgUIState.isError ->{
            LaunchedEffect(listbrgUIState.errorMessage) {
                listbrgUIState.errorMessage?.let {message ->
                    coroutineScope.launch {
                        snackbarHostState.showSnackbar(message)
                    }
                }
            }
        }
        listbrgUIState.listBrg.isEmpty() -> {
            Box(
                modifier= modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ){
                Text(text = "Tidak ada data mahasiswa",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(16.dp)
                )

            }
        }

        else -> {
            ListBarang(
                listBrg = listbrgUIState.listBrg,
                onClick = {
                    onClick(it)
                    println(
                        it
                    )
                },
                modifier = modifier
            )
        }
    }
}

@Composable
fun ListBarangView(
    viewModel: ListBarangViewModel = viewModel(factory = PenyediaViewModel.Factory),
    onDetailClick: (Int) -> Unit = { },
    onBack: () ->Unit = { },
    modifier: Modifier=Modifier
){
    Scaffold(
        topBar = {
            TopAppBar(
                judul = "Daftar Barang",
                showBackButton = true,
                onBack = onBack,
                modifier = modifier
            )
        }
    ) {innerPadding ->
        val listbrgUIState by viewModel.listbrgUIState.collectAsState()

        BodyListBarangView(
            listbrgUIState = listbrgUIState,
            onClick = {
                onDetailClick(it)
            },
            modifier = Modifier.padding(innerPadding)
        )
    }
}

