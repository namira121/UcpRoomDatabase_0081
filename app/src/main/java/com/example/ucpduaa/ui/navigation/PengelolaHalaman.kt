package com.example.ucpduaa.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.ucpduaa.ui.view.DestinasiInsertBrg
import com.example.ucpduaa.ui.view.DestinasiInsertSpl
import com.example.ucpduaa.ui.view.DetailBrgView
import com.example.ucpduaa.ui.view.HomeView
import com.example.ucpduaa.ui.view.InsertBarangView
import com.example.ucpduaa.ui.view.InsertSuplierView
import com.example.ucpduaa.ui.view.ListBarangView
import com.example.ucpduaa.ui.view.ListSuplierView
import com.example.ucpduaa.ui.view.UpdateBarangView

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
                },
                modifier = modifier
            )
        }
        composable(
            route = DestinasiInsertBrg.route
        ){
            InsertBarangView(
                onBack = {
                    navController.popBackStack()
                },
                onNavigate = {
                    navController.popBackStack()
                },
                modifier = modifier,
            )
        }
        composable(
            route = DestinasiInsertSpl.route
        ){
            InsertSuplierView(
                onBack = {
                    navController.popBackStack()
                },
                onNavigate = {navController.popBackStack()},
                modifier = modifier,
            )
        }
        composable(
            route = DestinasiBarang.route
        ){
            ListBarangView(
                onDetailClick = {id ->
                    navController.navigate("${DestinasiDetailBarang.route}/$id")
                    println(
                        "PengelolaHalaman : id = $id"
                    )
                },
                onBack = {
                    navController.popBackStack()
                },
                modifier = modifier,
            )
        }
        composable(
            route = DestinasiSuplier.route
        ){
            ListSuplierView(
                onBack = {
                    navController.popBackStack()
                },
                modifier = modifier
            )
        }
        composable(
            DestinasiDetailBarang.routesWithArg,
            arguments = listOf(
                navArgument(DestinasiDetailBarang.ID){
                    type = NavType.IntType
                }
            )
        ){
            val id = it.arguments?.getInt(DestinasiDetailBarang.ID)
            id?.let { id ->
                DetailBrgView(
                    onBack = {
                        navController.popBackStack()
                    },
                    onEditClick = {
                        navController.navigate("${DestinasiUpdateBarang.route}/$it")
                    },
                    modifier = modifier,
                    onDeleteClick = {
                        navController.popBackStack()
                    }
                )
            }
        }
        composable(
            DestinasiUpdateBarang.routesWithArg,
            arguments = listOf(
                navArgument(DestinasiUpdateBarang.ID){
                    type = NavType.IntType
                }
            )
        ){
            UpdateBarangView(
                onBack = {
                    navController.popBackStack()
                },
                onNavigate = {
                    navController.popBackStack()
                },
                modifier = modifier
            )
        }
    }
}