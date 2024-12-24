package com.example.ucpduaa.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ucpduaa.data.entity.Suplier
import com.example.ucpduaa.repository.RepositorySuplier
import kotlinx.coroutines.launch

data class SuplierEvent(
    val idspl: Int = 0,
    val namaspl: String = "",
    val kontakspl: String = "",
    val alamatspl: String = ""
)

fun SuplierEvent.toSuplierEntity(): Suplier = Suplier(
    idspl = idspl,
    namaspl = namaspl,
    kontakspl = kontakspl,
    alamatspl = alamatspl
)

data class FormErrorStateSpl(
    val idspl: Int? = null,
    val namaspl: String? = null,
    val kontakspl: String? = null,
    val alamatspl: String? = null,
) {
    fun isValid(): Boolean {
        return idspl == null && namaspl == null && kontakspl == null &&
                alamatspl == null
    }
}

data class SplUIState(
    val suplierEvent: SuplierEvent = SuplierEvent(),
    val isEntryValid: FormErrorStateSpl = FormErrorStateSpl(),
    val snackBarMessage: String? = null,
)

class InsertSuplierViewModel(private val repositorySuplier: RepositorySuplier): ViewModel(){
    var SuplieruiState by mutableStateOf(SplUIState())

    fun updateSplState(SuplierEvent: SuplierEvent){
        SuplieruiState = SuplieruiState.copy(
            suplierEvent = SuplierEvent,
        )
    }

    private fun validateFieldsSpl(): Boolean{
        val event = SuplieruiState.suplierEvent
        val errorState = FormErrorStateSpl(
            namaspl = if (event.namaspl.isNotEmpty()) null else "Nama tidak boleh kosong",
            kontakspl = if (event.kontakspl.isNotEmpty()) null else "Kontak tidak boleh kosong",
            alamatspl  = if (event.alamatspl.isNotEmpty()) null else "Alamat tidak boleh kosong"
        )
        SuplieruiState = SuplieruiState.copy(isEntryValid = errorState)
        return errorState.isValid()
    }

    fun saveDataSpl(){
        val currentEvent = SuplieruiState.suplierEvent

        if(validateFieldsSpl()){
            viewModelScope.launch {
                try{
                    repositorySuplier.insertsuplier(currentEvent.toSuplierEntity())
                    SuplieruiState = SuplieruiState.copy(
                        snackBarMessage = "Data berhasil disimpan",
                        suplierEvent = SuplierEvent(),
                        isEntryValid = FormErrorStateSpl()
                    )
                }catch (e: Exception){
                    SuplieruiState = SuplieruiState.copy(
                        snackBarMessage = "Data gagal disimpan"
                    )
                }
            }
        }else{
            SuplieruiState = SuplieruiState.copy(
                snackBarMessage = "Input Tidak Valid"
            )
        }
    }
    fun resetSnackBarMessage(){
        SuplieruiState = SuplieruiState.copy(snackBarMessage = null)
    }
}