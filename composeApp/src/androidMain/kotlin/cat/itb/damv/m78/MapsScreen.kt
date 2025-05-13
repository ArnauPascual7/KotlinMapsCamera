package cat.itb.damv.m78

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.android.gms.maps.GoogleMapOptions
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.*
import java.util.*

data class MarkerData(
    val id: String = UUID.randomUUID().toString(),
    var latLng: LatLng,
    var title: String = "",
    var description: String = "",
    val photo: String = ""
)

class MarkersViewModel : ViewModel() {
    val marker = mutableStateOf<MarkerData>(MarkerData(latLng = LatLng(41.45417990295869, 2.1856497659642167), title = "Itb"))
    val lastPhoto = mutableStateOf<String?>(null)
}

@SuppressLint("UnrememberedMutableState")
@Composable
fun MapsScreen(viewModel: MarkersViewModel = viewModel()){

    val itb = viewModel.marker.value

    val cameraPositionState: CameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(itb.latLng, 18f)
    }

    Box(Modifier.fillMaxSize()) {
        GoogleMap(
            googleMapOptionsFactory = {
                GoogleMapOptions().mapId("DEMO_MAP_ID")
            },
            cameraPositionState = cameraPositionState
        ) {
            Circle(
                center = itb.latLng,
                radius = 100.0,
                strokeColor = Color.Blue,
                fillColor = Color.Transparent
            )
            AdvancedMarker(
                state = MarkerState(position = itb.latLng),
                title = itb.title
            )
        }
    }
}