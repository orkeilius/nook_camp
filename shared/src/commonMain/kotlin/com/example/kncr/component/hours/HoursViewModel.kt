package com.example.kncr.component.hours

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kncr.utils.getCurrentTimeText
import com.example.kncr.utils.getMillisToNextMinute
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HoursViewModel : ViewModel() {
    private val _timeText = MutableStateFlow(getCurrentTimeText())
    val timeText: StateFlow<String> = _timeText.asStateFlow()

    init {
        viewModelScope.launch {
            while (true) {
                val delayMillis = getMillisToNextMinute()
                delay(delayMillis)
                _timeText.value = getCurrentTimeText()
            }
        }
    }
}