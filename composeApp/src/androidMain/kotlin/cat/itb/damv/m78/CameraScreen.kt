package cat.itb.damv.m78

import androidx.camera.compose.CameraXViewfinder
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import coil3.compose.AsyncImage

@Composable
fun CameraScreen(){
    FeatureThatRequiresCameraPermission {
        val viewModel = viewModel { CameraViewModel() }
        val context = LocalContext.current
        val lifecycleOwner = LocalLifecycleOwner.current
        val photoUrl = viewModel.photoUrl.value

        LaunchedEffect(lifecycleOwner) {
            viewModel.bindToCamera(context.applicationContext, lifecycleOwner)
        }
        val surfaceRequest = viewModel.surferRequest.value

        if (photoUrl == null) {
            surfaceRequest?.let { request ->
                Box {
                    CameraXViewfinder(
                        surfaceRequest = request,
                        modifier = Modifier.fillMaxSize()
                    )
                    Button({ viewModel.takePhoto(context) }) {
                        Text("Take Photo")
                    }
                }
            }
        }
        else {
            AsyncImage(
                model = photoUrl,
                contentDescription = null
            )
        }
    }
}