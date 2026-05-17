package com.example.kncr.component.hours

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.kncr.component.icon.AppIcon
import com.example.kncr.data.IconType

@Composable
fun HoursBoxComponent(viewModel: HoursViewModel, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .background(Color(0xFF4f535c))
            .padding(4.dp)
            .height(100.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        HourComponent(viewModel = viewModel)
        
        Spacer(modifier = Modifier.height(16.dp))
        
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxHeight()
        ) {
            AppIcon(
                iconType = IconType.Setting,
                color = Color.White,
                modifier = Modifier.size(24.dp)
            )
            AppIcon(
                iconType = IconType.TownMusic,
                color = Color.White,
                modifier = Modifier.size(24.dp)
            )
        }
    }
}
