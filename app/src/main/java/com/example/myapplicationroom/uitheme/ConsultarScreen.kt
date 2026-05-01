package com.example.myapplicationroom.uitheme

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.myapplicationroom.database.entities.Venta
import com.example.myapplicationroom.database.viewModels.ArticuloViewModel
import com.example.myapplicationroom.database.viewModels.VentaViewModel
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.material3.MenuAnchorType
import androidx.compose.material3.HorizontalDivider
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ConsultarScreen(onVolver: () -> Unit) {
    val context: Context = LocalContext.current
    val ventaViewModel = remember { VentaViewModel(context) }
    val articuloViewModel = remember { ArticuloViewModel(context) }

    var grupos by remember { mutableStateOf<List<Int>>(emptyList()) }
    var grupoSeleccionado by remember { mutableStateOf<Int?>(null) }
    var expandedGrupo by remember { mutableStateOf(false) }
    var ventas by remember { mutableStateOf<List<Venta>>(emptyList()) }
    var nombresArticulos by remember { mutableStateOf<Map<Int, String>>(emptyMap()) }
    var total by remember { mutableStateOf(0) }

    ventaViewModel.getAllVentas { lista ->
        grupos = lista.map { it.grupo }.distinct().sorted()
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
            text = "Consultar",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(24.dp))

        ExposedDropdownMenuBox(
            expanded = expandedGrupo,
            onExpandedChange = { expandedGrupo = !expandedGrupo },
            modifier = Modifier.fillMaxWidth()
        ) {
            OutlinedTextField(
                value = grupoSeleccionado?.toString() ?: "Selecciona un grupo",
                onValueChange = {},
                readOnly = true,
                label = { Text("Grupo") },
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandedGrupo) },
                modifier = Modifier
                    .fillMaxWidth()
                    .menuAnchor(MenuAnchorType.PrimaryNotEditable, true)
            )
            ExposedDropdownMenu(
                expanded = expandedGrupo,
                onDismissRequest = { expandedGrupo = false }
            ) {
                grupos.forEach { grupo ->
                    DropdownMenuItem(
                        text = { Text("Grupo $grupo") },
                        onClick = {
                            grupoSeleccionado = grupo
                            expandedGrupo = false
                        }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                val grupoId = grupoSeleccionado
                if (grupoId != null) {
                    ventaViewModel.getVentasByGrupo(grupoId) { lista ->
                        ventas = lista
                        total = lista.sumOf { it.valor }

                        lista.forEach { venta ->
                            articuloViewModel.getArticuloById(venta.id_articulo) { articulo ->
                                if (articulo != null) {
                                    nombresArticulos = nombresArticulos + (articulo.id to articulo.nombre)
                                }
                            }
                        }
                    }
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Buscar")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Lista de ventas
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            items(ventas) { venta ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                ) {
                    Column(modifier = Modifier.padding(12.dp)) {
                        Text(
                            text = nombresArticulos[venta.id_articulo] ?: "Artículo ${venta.id_articulo}",
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text("Precio: $${venta.valor / venta.cantidad}")
                            Text("Cantidad: ${venta.cantidad}")
                        }
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text("Valor: $${venta.valor}", fontWeight = FontWeight.SemiBold)
                        }
                    }
                }
            }
        }

        // Total
        if (ventas.isNotEmpty()) {
            Spacer(modifier = Modifier.height(8.dp))
            HorizontalDivider()
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Total",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "$$total",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
        }

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
fun ConsultarScreenPreview() {
    ConsultarScreen(onVolver = {})
}