package com.example.kncr.service

import eu.iamkonstantin.kotlin.gadulka.GadulkaPlayer
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch


class AudioService {
    private val scope = CoroutineScope(Dispatchers.Default + SupervisorJob())
    private var player = GadulkaPlayer()

    fun start() {
        scope.launch {audioloop()}
    }

    fun audioloop(){
        player.play(url = "https://download.samplelib.com/wav/sample-12s.wav")
    }


}