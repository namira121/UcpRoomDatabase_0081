package com.example.ucpduaa.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.example.ucpduaa.data.entity.Barang
import com.example.ucpduaa.repository.RepositoryBarang
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart

data class ListBrgUIState(
    val listBrg: List<Barang> = listOf(),
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String = ""
)

class ListBarangViewModel(
    private  val repositoryBarang: RepositoryBarang
):ViewModel(){
    val listbrgUIState: StateFlow<ListBrgUIState> = repositoryBarang.getAllBarang()
        .filterNotNull()
        .map {
            ListBrgUIState(
                listBrg = it.toList(),
                isLoading = false
            )
        }
        .onStart {
            emit(ListBrgUIState(isLoading = true))
            delay(900)
        }
        .catch {
            emit(
                ListBrgUIState(
                    isLoading = false,
                    isError = true,
                    errorMessage = it.message?:"Terjadi Kesalahan"
                )
            )
        }
}