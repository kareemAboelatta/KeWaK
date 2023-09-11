package com.example.kewaapp.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.example.kewaapp.R
import kotlinx.coroutines.delay

private const val SplashWaitTime: Long = 2000

@Composable
fun LandingScreen(onTimeout: () -> Unit, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        // Adds composition consistency. Use the value when LaunchedEffect is first called
        val currentOnTimeout by
        rememberUpdatedState(onTimeout)

        LaunchedEffect(Unit) {
            delay(SplashWaitTime)
            currentOnTimeout()
        }
        Image(painterResource(id = R.drawable.ic_crane_drawer), contentDescription = null)
    }
}
