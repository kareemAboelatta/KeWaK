package com.example.kewaapp.presentation


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.kewaapp.domain.entities.User
import com.example.kewaapp.presentation.screens.auth.AuthScreen
import com.example.kewaapp.presentation.screens.LandingScreen
import com.example.kewaapp.presentation.theme.KewaAppTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : ComponentActivity() {


    @Inject
    lateinit var user: User

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KewaAppTheme {
                // A surface container using the 'background' color from the theme
                MainScreen()
            }
        }
    }
}



@Composable
private fun MainScreen() {
    Surface(color = MaterialTheme.colorScheme.background) {
        var showLandingScreen by remember {
            mutableStateOf(true)
        }
        if (showLandingScreen) {
            LandingScreen(onTimeout = { showLandingScreen = false })
        } else {
            AuthScreen()
        }
    }
}
