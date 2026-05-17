package com.example.kncr.data

import kotlin.math.roundToInt

@ConsistentCopyVisibility
data class VolumeUnit private constructor(val percent: Float) {
    companion object {
        const val VOLUME_STEP = 0.1f

        operator fun invoke(percent: Float): VolumeUnit {
            return VolumeUnit(roundToStep(percent.coerceIn(0f, 1f)))
        }

        fun roundToStep(value: Float): Float {
            val multiply = 1f / VOLUME_STEP
            return (value * multiply).roundToInt() / multiply
        }
    }

    fun volumeUp(): VolumeUnit {
        return invoke(percent + VOLUME_STEP)
    }

    fun volumeDown(): VolumeUnit {
        return invoke(percent - VOLUME_STEP)
    }
}
