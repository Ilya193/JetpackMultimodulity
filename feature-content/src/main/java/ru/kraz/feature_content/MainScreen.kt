package ru.kraz.feature_content

import androidx.compose.foundation.clickable
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.koin.androidx.compose.koinViewModel

@Composable
fun MainScreen(viewModel: ContentViewModel = koinViewModel()) {
    Text(modifier = Modifier.clickable {
        viewModel.openMarsAPI()
    }, text = "MainScreen")
}