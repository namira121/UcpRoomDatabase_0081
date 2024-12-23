package com.example.ucpduaa.ui.viewmodel

import com.example.ucpduaa.data.entity.Barang
import com.example.ucpduaa.data.entity.Suplier

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
    val barangEvent: BarangEvent = BarangEvent(),
    val isEntryValid: FormErrorState = FormErrorState(),
    val snackBarMessage: String? = null,
)