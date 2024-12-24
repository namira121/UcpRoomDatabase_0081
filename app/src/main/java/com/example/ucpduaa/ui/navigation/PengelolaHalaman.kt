package com.example.ucpduaa.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.ucpduaa.ui.view.DestinasiInsertBrg
import com.example.ucpduaa.ui.view.DestinasiInsertSpl
import com.example.ucpduaa.ui.view.HomeView

@Composable
fun PengelolaHalaman(
    navController: NavHostController= rememberNavController(),
    modifier: Modifier = Modifier
){
    NavHost(
        navController = navController,
        startDestination = DestinasiHome.route){
        composable(
            route = DestinasiHome.route
        ){
            HomeView(
                onAddBarang = {
                    navController.navigate(DestinasiInsertBrg.route)
                },
                onAddSuplier = {
                    navController.navigate(DestinasiInsertSpl.route)
                },
                onListBarang = {
                    navController.navigate(DestinasiBarang.route)
                },
                onListSuplier = {
                    navController.navigate(DestinasiSuplier.route)
                }
            )
        }
    }
}