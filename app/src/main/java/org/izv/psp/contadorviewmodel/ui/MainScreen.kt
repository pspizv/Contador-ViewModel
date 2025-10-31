package org.izv.psp.contadorviewmodel.ui

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import org.izv.psp.contadorviewmodel.ui.theme.ContadorViewModelTheme

@Composable
fun MainScreen() {
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        CounterScreen(innerPadding)
    }
}

@Composable
fun CounterScreen(innerPadding: PaddingValues) {
    val viewModel: CounterViewModel = viewModel(
        factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return CounterViewModel() as T
            }
        }
    )
    val count by viewModel.count.collectAsState()
    Button(
        modifier = Modifier.padding(innerPadding),
        onClick = viewModel::increment) {
            Text("Contador: $count")
    }
}

@Preview(showBackground = true)
@Composable
fun CounterPreview() {
    ContadorViewModelTheme {
        MainScreen()
    }
}