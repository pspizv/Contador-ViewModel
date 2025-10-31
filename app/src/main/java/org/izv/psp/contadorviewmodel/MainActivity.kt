package org.izv.psp.contadorviewmodel

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import org.izv.psp.contadorviewmodel.ui.MainScreen
import org.izv.psp.contadorviewmodel.ui.theme.ContadorViewModelTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ContadorViewModelTheme {
                MainScreen()
            }
        }
    }
}