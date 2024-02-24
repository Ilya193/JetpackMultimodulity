package ru.kraz.jetpackmultimodulity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.koin.androidx.compose.koinViewModel
import ru.kraz.feature_content.MainScreen
import ru.kraz.feature_mars.MarsAPIScreen
import ru.kraz.jetpackmultimodulity.ui.theme.JetpackMultimodulityTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackMultimodulityTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Content()
                }
            }
        }
    }
}

@Composable
fun Content(viewModel: MainViewModel = koinViewModel()) {
    val navController = rememberNavController()
    val screen by viewModel.read().collectAsState()

    DisposableEffect(Unit) {
        onDispose {
            viewModel.coup()
        }
    }

    LaunchedEffect(Unit) {
        snapshotFlow {
            screen
        }.collect {
            screen.show(navController)
        }
    }

    NavHost(navController = navController, startDestination = "Main") {
        composable("Main") {
            MainScreen()
        }
        composable("MarsAPI") {
            MarsAPIScreen()
        }
    }
}