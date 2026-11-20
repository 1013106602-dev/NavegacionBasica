package com.ejemplo.navegacion

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

object Rutas {
    const val INICIO = "inicio"
    const val LUGARES = "lugares"
    const val PERFIL = "perfil"
    const val DETALLE = "detalle/{id}"

    fun detalle(id: Int) = "detalle/$id"
}

private data class Destino(
    val ruta: String,
    val titulo: String,
    val icono: ImageVector
)

private val destinos = listOf(
    Destino(Rutas.INICIO, "Inicio", Icons.Default.Home),
    Destino(Rutas.LUGARES, "Lugares", Icons.Default.Menu),
    Destino(Rutas.PERFIL, "Perfil", Icons.Default.Person)
)

@Composable
fun AppNavegacion() {
    val navController = rememberNavController()
    val entradaActual by navController.currentBackStackEntryAsState()
    val rutaActual = entradaActual?.destination?.route

    Scaffold(
        bottomBar = {
            if (destinos.any { it.ruta == rutaActual }) {
                BarraInferior(navController, rutaActual)
            }
        }
    ) { padding ->
        NavHost(
            navController = navController,
            startDestination = Rutas.INICIO,
            modifier = Modifier.padding(padding)
        ) {
            composable(Rutas.INICIO) {
                PantallaInicio(onVerLugares = { navController.navigate(Rutas.LUGARES) })
            }
            composable(Rutas.LUGARES) {
                PantallaLugares(onLugarClick = { id -> navController.navigate(Rutas.detalle(id)) })
            }
            composable(Rutas.PERFIL) {
                PantallaPerfil()
            }
            composable(
                route = Rutas.DETALLE,
                arguments = listOf(navArgument("id") { type = NavType.IntType })
            ) { backStackEntry ->
                val id = backStackEntry.arguments?.getInt("id") ?: 0
                PantallaDetalle(
                    lugar = lugares.first { it.id == id },
                    onVolver = { navController.popBackStack() }
                )
            }
        }
    }
}

@Composable
private fun BarraInferior(navController: NavHostController, rutaActual: String?) {
    NavigationBar {
        destinos.forEach { destino ->
            NavigationBarItem(
                selected = rutaActual == destino.ruta,
                onClick = {
                    navController.navigate(destino.ruta) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                icon = { Icon(destino.icono, contentDescription = destino.titulo) },
                label = { Text(destino.titulo) }
            )
        }
    }
}
