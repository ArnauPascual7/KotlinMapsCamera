package cat.itb.damv.m78

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun MarkersScreen(viewModel: MarkersViewModel = viewModel(), navViewModel: NavViewModel = viewModel()) {
    val markers = viewModel.markers

    Column(
        Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(Modifier.height(20.dp))
        Text("Marcadors", fontSize = 30.sp, fontWeight = FontWeight.Bold, textAlign = TextAlign.Center)

        Spacer(Modifier.height(20.dp))
        Button(onClick = { navViewModel.navTo(Screen.AddMarker) }) {
            Text("Afegir un marcador")
        }

        Spacer(Modifier.height(20.dp))
        LazyColumn {
            items(markers) { marker ->
                Row(
                    modifier = Modifier.fillMaxWidth().padding(start = 20.dp, end = 20.dp).border(
                        width = 2.dp,
                        brush = Brush.horizontalGradient(listOf(Color.Red, Color.Blue)),
                        shape = RectangleShape
                    ),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "${marker.title}: Lat: ${marker.latLng.latitude}, Lng: ${marker.latLng.longitude}",
                        modifier = Modifier.padding(start = 20.dp),
                        fontWeight = FontWeight.Bold
                    )
                    IconButton(onClick = {
                        navViewModel.selectedMarkerId = marker.id
                        navViewModel.navTo(Screen.Details)
                    }) {
                        Icon(Icons.Filled.Edit, contentDescription = "Editar")
                    }
                    IconButton(onClick = { viewModel.markers.remove(marker) }) {
                        Icon(Icons.Filled.Close, contentDescription = "Eliminar")
                    }
                }
                Spacer(Modifier.height(10.dp))
            }
        }
    }
}