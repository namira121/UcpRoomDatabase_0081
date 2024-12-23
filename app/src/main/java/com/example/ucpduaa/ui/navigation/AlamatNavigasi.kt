package com.example.ucpduaa.ui.navigation

interface AlamatNavigasi{
    val route: String
}

object DestinasiHome : AlamatNavigasi{
    override val route = "home"
}

object DestinasiBarang :AlamatNavigasi{
    override val route = "daftarbarang"
}

object DestinasiDetailBarang : AlamatNavigasi{
    override val route = "detailbarang"
    const val ID = "id"
    val routesWithArg = "$route/{$ID}"
}

object DestinasiUpdateBarang : AlamatNavigasi{
    override val route = "updatebarang"
    const val ID = "id"
    val routesWithArg = "$route/{$ID}"
}