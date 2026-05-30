package com.example.kncr.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.kncr.data.VolumeType
import com.example.kncr.component.hours.HoursBoxComponent
import com.example.kncr.component.volume.VolumeComponent
import com.example.kncr.component.hours.HoursViewModel
import com.example.kncr.component.subMenuButton.SubMenuButton
import com.example.kncr.component.subMenuButton.SubMenuButtonType
import com.example.kncr.component.volume.VolumeViewModel

@Preview
@Composable
fun MainScreen() {
    val hoursViewModel = remember { HoursViewModel() }
    val musicViewModel = remember { VolumeViewModel(VolumeType.Music) }
    val rainViewModel = remember { VolumeViewModel(VolumeType.Rain) }

    Column(
        modifier = Modifier.fillMaxSize().background(Color(0xFF353840)),
    ) {

        Row() {
            HoursBoxComponent(viewModel = hoursViewModel, modifier = Modifier.padding(5.dp))

            VolumeComponent(viewModel = musicViewModel)
            VolumeComponent(viewModel = rainViewModel)
        }
        Row() {
            SubMenuButton(SubMenuButtonType.Playlist)
            SubMenuButton(SubMenuButtonType.Setting)
        }
    }
}
