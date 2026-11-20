package com.ejemplo.navegacion

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun PantallaInicio(onVerLugares: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text("Bienvenido", style = MaterialTheme.typography.headlineMedium)
        Text(
            "Esta app muestra cómo moverse entre pantallas con Navigation Compose.",
            modifier = Modifier.padding(top = 8.dp, bottom = 24.dp)
        )
        Button(onClick = onVerLugares) {
            Text("Ver lugares")
        }
    }
}

@Composable
fun PantallaLugares(onLugarClick: (Int) -> Unit) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        item {
            Text(
                "Lugares de Bogotá",
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.padding(16.dp)
            )
        }
        items(lugares) { lugar ->
            ListItem(
                headlineContent = { Text(lugar.nombre) },
                supportingContent = { Text(lugar.descripcion, maxLines = 1) },
                modifier = Modifier.clickable { onLugarClick(lugar.id) }
            )
            HorizontalDivider()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaDetalle(lugar: Lugar, onVolver: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(lugar.nombre) },
                navigationIcon = {
                    IconButton(onClick = onVolver) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Volver")
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
        ) {
            Text(lugar.descripcion, style = MaterialTheme.typography.bodyLarge)
        }
    }
}

@Composable
fun PantallaPerfil() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text("Perfil", style = MaterialTheme.typography.headlineMedium)
        Text(
            "Nombre: Karen Rueda´, Edad: 21 , Fecha de naciemiento: 7 de octubre 2005", 
            modifier = Modifier.padding(top = 8.dp)
        )
    }
}
