package com.example.ucpduaa.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ucpduaa.data.entity.Barang
import com.example.ucpduaa.data.entity.Suplier
import com.example.ucpduaa.repository.RepositoryBarang
import com.example.ucpduaa.repository.RepositorySuplier
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn

data class ListSplUIState(
    val listSpl: List<Suplier> = listOf(),
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String = ""
)

class ListSuplierViewModel(
    private  val repositorySuplier: RepositorySuplier
): ViewModel(){
    val listsplUIState: StateFlow<ListSplUIState> = repositorySuplier.getAllSuplier()
        .filterNotNull()
        .map {
            ListSplUIState(
                listSpl = it.toList(),
                isLoading = false
            )
        }
        .onStart {
            emit(ListSplUIState(isLoading = true))
            delay(900)
        }
        .catch {
            emit(
                ListSplUIState(
                    isLoading = false,
                    isError = true,
                    errorMessage = it.message?:"Terjadi Kesalahan"
                )
            )
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = ListSplUIState(
                isLoading = true
            )
        )
}