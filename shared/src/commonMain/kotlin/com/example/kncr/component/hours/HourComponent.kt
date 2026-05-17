package com.example.kncr.component.hours

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.sp
import kncr.shared.generated.resources.Monocraft
import kncr.shared.generated.resources.Res
import org.jetbrains.compose.resources.Font

@Composable
fun HourComponent(viewModel: HoursViewModel, modifier: Modifier = Modifier) {
    val timeText by viewModel.timeText.collectAsState()

    Box(modifier = modifier, contentAlignment = Alignment.Center) {
        Text(
            text = timeText,
            color = Color.White,
            fontSize = 32.sp,
            fontFamily = FontFamily(Font(Res.font.Monocraft)),
        )
    }
}
