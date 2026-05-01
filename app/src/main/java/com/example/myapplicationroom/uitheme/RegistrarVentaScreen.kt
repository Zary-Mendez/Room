package com.example.myapplicationroom.uitheme

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.myapplicationroom.database.entities.Articulo
import com.example.myapplicationroom.database.viewModels.ArticuloViewModel
import com.example.myapplicationroom.database.viewModels.VentaViewModel
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.material3.MenuAnchorType

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegistrarVentaScreen(onVolver: () -> Unit) {
    val context: Context = LocalContext.current
    val articuloViewModel = remember { ArticuloViewModel(context) }
    val ventaViewModel = remember { VentaViewModel(context) }

    var articulos by remember { mutableStateOf<List<Articulo>>(emptyList()) }
    var articuloSeleccionado by remember { mutableStateOf<Articulo?>(null) }
    var expandedArticulo by remember { mutableStateOf(false) }

    var grupo by remember { mutableStateOf("") }
    var cantidad by remember { mutableStateOf("") }
    var mensaje by remember { mutableStateOf("") }

    LaunchedEffect(Unit) {
        articuloViewModel.getArticulos { lista ->
            articulos = lista
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Registrar venta",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(24.dp))

        ExposedDropdownMenuBox(
            expanded = expandedArticulo,
            onExpandedChange = { expandedArticulo = !expandedArticulo },
            modifier = Modifier.fillMaxWidth()
        ) {
            OutlinedTextField(
                value = articuloSeleccionado?.nombre ?: "Selecciona un artículo",
                onValueChange = {},
                readOnly = true,
                label = { Text("Artículo") },
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandedArticulo) },
                modifier = Modifier
                    .fillMaxWidth()
                    .menuAnchor(MenuAnchorType.PrimaryNotEditable, true)
            )
            ExposedDropdownMenu(
                expanded = expandedArticulo,
                onDismissRequest = { expandedArticulo = false }
            ) {
                articulos.forEach { articulo ->
                    DropdownMenuItem(
                        text = { Text("${articulo.id} - ${articulo.nombre}") },
                        onClick = {
                            articuloSeleccionado = articulo
                            expandedArticulo = false
                        }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = grupo,
            onValueChange = { grupo = it },
            label = { Text("Grupo") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = cantidad,
            onValueChange = { cantidad = it },
            label = { Text("Cantidad") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(24.dp))

        if (mensaje.isNotEmpty()) {
            Text(
                text = mensaje,
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.height(8.dp))
        }

        Button(
            onClick = {
                val grupoInt = grupo.toIntOrNull()
                val cantidadInt = cantidad.toIntOrNull()

                if (articuloSeleccionado == null || grupoInt == null || cantidadInt == null || cantidadInt <= 0) {
                    mensaje = "Por favor completa todos los campos correctamente"
                } else {
                    ventaViewModel.saveNewVenta(
                        grupo = grupoInt,
                        idArticulo = articuloSeleccionado!!.id,
                        cantidad = cantidadInt
                    ) { success ->
                        if (success) {
                            mensaje = "Venta registrada correctamente"
                            articuloSeleccionado = null
                            grupo = ""
                            cantidad = ""
                        } else {
                            mensaje = "Error: artículo no encontrado"
                        }
                    }
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Guardar")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = onVolver,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Volver")
        }
    }
}
@Preview(showBackground = true, widthDp = 360, heightDp = 640)
@Composable
fun RegistrarVentaScreenPreview() {
    RegistrarVentaScreen(onVolver = {})
}