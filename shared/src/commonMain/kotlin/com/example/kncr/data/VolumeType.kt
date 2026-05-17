package com.example.kncr.data

import androidx.compose.ui.graphics.Color

enum class VolumeType {
    Music,
    Rain;

    val color: Color
        get() = when (this) {
            Music -> Color(0xFF8959D6) // #8959d6
            Rain -> Color(0xFF1AB3DD) // #1ab3dd
        }

    val icon: IconType
        get() = when (this) {
            Music -> IconType.Music
            Rain -> IconType.Rain
        }
}
