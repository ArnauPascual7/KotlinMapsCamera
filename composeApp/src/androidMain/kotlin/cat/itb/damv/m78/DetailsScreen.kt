package cat.itb.damv.m78

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun MarkerDetailScreen(
    markerId: String,
    navViewModel: NavViewModel,
    markersViewModel: MarkersViewModel = viewModel()
) {
    val marker = markersViewModel.getMarkerById(markerId) ?: return
    var title by remember { mutableStateOf(marker.title) }
    var description by remember { mutableStateOf(marker.description) }

    Column(modifier = Modifier.padding(16.dp)) {
        Text("Editar marcador", fontWeight = FontWeight.Bold, fontSize = 20.sp)
        Spacer(Modifier.height(10.dp))
        OutlinedTextField(
            value = title,
            onValueChange = { title = it },
            label = { Text("Títol") }
        )
        Spacer(Modifier.height(10.dp))
        OutlinedTextField(
            value = description,
            onValueChange = { description = it },
            label = { Text("Descripció") }
        )
        Spacer(Modifier.height(20.dp))
        Button(onClick = {
            markersViewModel.updateMarker(marker.copy(title = title, description = description))
            navViewModel.navTo(Screen.Markers)
        }) {
            Text("Desar")
        }
    }
}
