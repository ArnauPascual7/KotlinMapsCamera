package cat.itb.damv.m78

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            //App()
            //MapsScreen()
            //Navigation()
            CameraScreen()
        }
    }
}

@Preview
@Composable
fun AppAndroidPreview() {
    App()
}