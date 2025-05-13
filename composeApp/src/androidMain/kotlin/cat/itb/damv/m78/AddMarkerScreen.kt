package cat.itb.damv.m78

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.android.gms.maps.model.LatLng

/*@Composable
fun AddMarkerScreen(
    viewModel: MarkersViewModel = viewModel(),
    onAddMarker: (MarkerData) -> Unit,
    onCancel: () -> Unit
) {
    var lat by remember { mutableStateOf("") }
    var lng by remember { mutableStateOf("") }
    var title by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Afegir un marcador", fontSize = 24.sp, fontWeight = FontWeight.Bold)

        Spacer(modifier = Modifier.height(20.dp))

        TextField(
            value = lat,
            onValueChange = { lat = it },
            label = { Text("Latitud") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
        )
        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = lng,
            onValueChange = { lng = it },
            label = { Text("Longitud") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
        )
        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = title,
            onValueChange = { title = it },
            label = { Text("Títol") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        if (errorMessage.isNotEmpty()) {
            Text(text = errorMessage, color = Color.Red)
        }

        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Button(onClick = {
                if (lat.isNotEmpty() && lng.isNotEmpty() && title.isNotEmpty()) {
                    val newMarker = MarkerData(
                        latLng = LatLng(lat.toDouble(), lng.toDouble()),
                        title = title
                    )
                    onAddMarker(newMarker)
                    viewModel.markers.add(newMarker)
                } else {
                    errorMessage = "Tots els camps són obligatoris"
                }
            }) {
                Text("Afegir")
            }
            Button(onClick = { onCancel() }) {
                Text("Cancelar")
            }
        }
    }
}
*/