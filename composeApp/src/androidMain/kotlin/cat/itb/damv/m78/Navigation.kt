package cat.itb.damv.m78

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.launch

sealed interface Screen {
    data object Map: Screen
    data object Details: Screen
    data object Camera: Screen
}

class NavViewModel : ViewModel() {
    val currentScreen = mutableStateOf<Screen>(Screen.Map)

    fun navTo(screen: Screen) { currentScreen.value = screen }
}

@OptIn(InternalComposeApi::class, ExperimentalMaterial3Api::class)
@Composable
fun Navigation() {
    val navViewModel = viewModel { NavViewModel() }
    val currentScreen = navViewModel.currentScreen.value

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerContent = {
            ModalDrawerSheet {
                Text("Menú")
                NavigationDrawerItem(
                    label = { Text("Mapa") },
                    selected = false,
                    icon = { Icon(Icons.Default.Place, contentDescription = "Map") },
                    onClick = {
                        navViewModel.navTo(Screen.Map)
                        scope.launch { drawerState.close() }
                    }
                )
                NavigationDrawerItem(
                    label = { Text("Càmera") },
                    selected = false,
                    icon = { Icon(Icons.Default.Search, contentDescription = "Markers") },
                    onClick = {
                        navViewModel.navTo(Screen.Camera)
                        scope.launch { drawerState.close() }
                    }
                )
                NavigationDrawerItem(
                    label = { Text("Detalls") },
                    selected = false,
                    icon = { Icon(Icons.Default.Search, contentDescription = "Detalls") },
                    onClick = {
                        navViewModel.navTo(Screen.Details)
                        scope.launch { drawerState.close() }
                    }
                )
            }
        },
        drawerState = drawerState,
        gesturesEnabled = false
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("Menú") },
                    navigationIcon = {
                        IconButton(onClick = {
                            scope.launch {
                                drawerState.apply {
                                    if (isClosed) open() else close()
                                }
                            }
                        }) {
                            Icon(
                                imageVector = Icons.Default.Menu,
                                contentDescription = "Menú"
                            )
                        }
                    }
                )
            }
            ) { padding ->
            Box(modifier = Modifier.padding(padding)) {
                when (currentScreen) {
                    Screen.Map -> MapsScreen()
                    Screen.Camera -> CameraScreen()
                    Screen.Details -> MarkerDetailScreen()
                    /*Screen.Details -> navViewModel.selectedMarkerId?.let { markerId ->
                        MarkerDetailScreen(markerId, navViewModel)
                    }
                    Screen.AddMarker -> AddMarkerScreen(
                        onAddMarker = { marker ->
                            navViewModel.navTo(Screen.Markers)
                        },
                        onCancel = {
                            navViewModel.navTo(Screen.Markers)
                        }
                    )*/
                }
            }
        }
    }
}