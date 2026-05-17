package com.example.kncr.component.volume

import androidx.lifecycle.ViewModel
import com.example.kncr.data.VolumeType
import com.example.kncr.data.VolumeUnit
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class VolumeViewModel(val volumeType: VolumeType) : ViewModel() {
    private val _volume = MutableStateFlow(VolumeUnit.Companion(0.7f))
    val volume: StateFlow<VolumeUnit> = _volume.asStateFlow()

    fun handleOnMouseDrag(posRelativeToTargetY: Float) {
        _volume.update { VolumeUnit.Companion(1f - posRelativeToTargetY) }

    }

    fun handleOnScroll(deltaY: Float) {
        _volume.update { current ->
            if (deltaY < 0f) {
                current.volumeUp()
            } else {
                current.volumeDown()
            }
        }
    }
}