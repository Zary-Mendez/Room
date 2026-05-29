package com.example.myapplicationroom.uitheme

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
enum class Pantalla {
    MAIN, CONSULTAR, REGISTRAR_VENTA, REGISTRAR_ARTICULO
}

@Composable
fun AppContent() {
    var pantallaActual by remember { mutableStateOf(Pantalla.MAIN) }

    when (pantallaActual) {
        Pantalla.MAIN -> MainScreen(
            onConsultar = { pantallaActual = Pantalla.CONSULTAR },
            onRegistrarVenta = { pantallaActual = Pantalla.REGISTRAR_VENTA },
            onRegistrarArticulo = { pantallaActual = Pantalla.REGISTRAR_ARTICULO }
        )
        Pantalla.CONSULTAR -> ConsultarScreen(
            onVolver = { pantallaActual = Pantalla.MAIN }
        )
        Pantalla.REGISTRAR_VENTA -> RegistrarVentaScreen(
            onVolver = { pantallaActual = Pantalla.MAIN }
        )
        Pantalla.REGISTRAR_ARTICULO -> RegistrarArticuloScreen(
            onVolver = { pantallaActual = Pantalla.MAIN }
        )
    }
}

@Composable
fun MainScreen(
    onConsultar: () -> Unit,
    onRegistrarVenta: () -> Unit,
    onRegistrarArticulo: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Registrar ventas",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(48.dp))

        Button(
            onClick = onConsultar,
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp)
        ) {
            Text("Consultar")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = onRegistrarVenta,
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp)
        ) {
            Text("Registrar venta")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = onRegistrarArticulo,
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp)
        ) {
            Text("Registrar artículo")
        }
    }
}
@Preview(showBackground = true, widthDp = 360, heightDp = 640)
@Composable
fun MainScreenPreview() {
    MainScreen(
        onConsultar = {},
        onRegistrarVenta = {},
        onRegistrarArticulo = {}
    )
}