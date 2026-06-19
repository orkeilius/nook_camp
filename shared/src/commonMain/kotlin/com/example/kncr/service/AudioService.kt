package com.example.kncr.service

import eu.iamkonstantin.kotlin.gadulka.GadulkaPlayer
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch


class AudioService {
    private val scope = CoroutineScope(Dispatchers.Default + SupervisorJob())
    private var player = GadulkaPlayer()

    fun start() {
        try {
            player.play(url = "https://download.samplelib.com/wav/sample-12s.wav")
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun stop() {
        player.release()
    }


}