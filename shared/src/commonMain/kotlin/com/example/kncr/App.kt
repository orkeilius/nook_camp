package com.example.kncr

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import com.example.kncr.screen.MainScreen
import com.example.kncr.service.AudioService

@Composable
@Preview
fun App() {
    val audio = remember { AudioService() }
    DisposableEffect(Unit) {
        audio.start()
        onDispose { audio.stop() }
    }
    MaterialTheme {
        MainScreen()
    }
}