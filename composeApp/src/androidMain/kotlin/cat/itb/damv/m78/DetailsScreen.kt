package cat.itb.damv.m78

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil3.compose.AsyncImage
import org.jetbrains.annotations.Async

@Composable
fun MarkerDetailScreen(markViewModel: MarkersViewModel = viewModel(), navViewModel: NavViewModel = viewModel(), camViewModel: CameraViewModel = viewModel()) {
    val itb = markViewModel.marker.value
    val lastPhoto = camViewModel.photoUrl.value

    var title by remember { mutableStateOf(itb.title) }
    var description by remember { mutableStateOf(itb.description) }
    var photo by remember { mutableStateOf(itb.photo) }


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

        if (photo.isNotEmpty()) {
            AsyncImage(
                model = photo,
                contentDescription = null,
                modifier = Modifier.size(200.dp)
            )
        }

        if (lastPhoto != null) {
            if (photo.isEmpty()) {
                Button(onClick = {
                    photo = camViewModel.photoUrl.value.toString()
                }) {
                    Text("Utilitzar última foto")
                }
            }
        }
        else {
            Button(onClick = {
                navViewModel.navTo(Screen.Camera)
            }) {
                Text("Fer Foto")
            }
        }


        Spacer(Modifier.height(20.dp))
        Button(onClick = {
            markViewModel.marker.value = itb.copy(title = title, description = description, photo = photo)
        }) {
            Text("Desar")
        }
    }
}