package com.example.kncr.component.volume

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.awaitEachGesture
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerEventType
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.kncr.component.icon.AppIcon
import com.example.kncr.data.VolumeType

@Preview
@Composable
fun VolumeComponent(
    viewModel: VolumeViewModel = VolumeViewModel(VolumeType.Music),
    modifier: Modifier = Modifier,
) {
    val volume by viewModel.volume.collectAsState()
    val type = viewModel.volumeType

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        Box(
            contentAlignment = Alignment.BottomCenter,
            modifier = Modifier
                .testTag("volumeBar")
                .width(20.dp)
                .height(120.dp)
                .background(Color(0xFFE0E0E0))
                .scrollHandler(viewModel)
                .dragHandler(viewModel)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(volume.percent)
                    .background(type.color)
            )
        }

        Spacer(modifier = Modifier.height(10.dp))

        AppIcon(
            iconType = type.icon,
            color = if (volume.percent == 0f) Color(0xFF888888) else type.color,
            modifier = Modifier.size(24.dp)
        )
    }
}

private fun Modifier.scrollHandler(viewModel: VolumeViewModel): Modifier = pointerInput(Unit) {
    awaitPointerEventScope {
        while (true) {
            val event = awaitPointerEvent()
            if (event.type != PointerEventType.Scroll) continue
            val deltaY = event.changes.firstOrNull()?.scrollDelta?.y ?: 0f
            if (deltaY == 0f) continue
            viewModel.handleOnScroll(deltaY)
        }
    }
}

private fun Modifier.dragHandler(viewModel: VolumeViewModel): Modifier = pointerInput(Unit) {
    awaitEachGesture {
        val down = awaitFirstDown()
        viewModel.handleOnMouseDrag(down.position.y / size.height)
        do {
            val event = awaitPointerEvent()
            if (event.type == PointerEventType.Move && event.changes.any { it.pressed }) {
                viewModel.handleOnMouseDrag(event.changes.first().position.y / size.height)
            }
        } while (event.changes.any { it.pressed })
    }
}
