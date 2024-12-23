package com.example.ucpduaa.ui.view

import android.app.AlertDialog
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ucpduaa.ui.customwidget.TopAppBar
import com.example.ucpduaa.ui.viewmodel.DetailBarangViewModel
import com.example.ucpduaa.ui.viewmodel.DetailBrgUIState
import com.example.ucpduaa.ui.viewmodel.PenyediaViewModel

@Composable
fun DetailBrgView(
    modifier: Modifier = Modifier,
    viewModel: DetailBarangViewModel = viewModel(factory = PenyediaViewModel.Factory),
    onBack: () -> Unit = { },
    onEditClick: (Int) -> Unit = { },
    onDeleteClick: () -> Unit = { }
){
    Scaffold(
        topBar = {
            TopAppBar(
                judul = "Detail Barang",
                showBackButton = true,
                onBack = onBack,
                modifier = modifier
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    onEditClick(viewModel.detailbrgUIState.value.detailUiEvent.id) },
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier.padding(16.dp)
            ) {
                Icon(imageVector = Icons.Default.Edit,
                    contentDescription = "Edit Barang")

            }
        }
    ) { innerPadding ->
        val detailBrgUIState by viewModel.detailbrgUIState.collectAsState()

        BodyDetailBrg(
            modifier = Modifier.padding(innerPadding),
            detailBrgUIState = detailBrgUIState,
            onDeleteClick={
                viewModel.deleteBrg()
                onDeleteClick()
            }
        )
    }
}

@Composable
fun BodyDetailBrg(
    modifier: Modifier = Modifier,
    detailBrgUIState: DetailBrgUIState = DetailBrgUIState(),
    onDeleteClick: () -> Unit = { }
){}

@Composable
private fun DeleteConfirmationDialog(
    onDeleteConfirm: () -> Unit,
    onDeleteCancel: () -> Unit,
    modifier: Modifier = Modifier
){
    AlertDialog(onDismissRequest = { },
        title = { Text("Delete Data") },
        text={ Text("Apaka anda ingin manghapus data ini?") },
        modifier = modifier,
        dismissButton = {
            TextButton(onClick = onDeleteCancel) {
                Text(text = "Cancel")
            }
        },
        confirmButton = {
            TextButton(onClick = onDeleteConfirm) {
                Text(text = "Yes")
            }
        }
        )
}