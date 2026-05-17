package com.example.kncr.component.icon

import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.addPathNodes
import androidx.compose.ui.unit.dp
import com.example.kncr.data.IconType

@Composable
fun AppIcon(iconType: IconType, color: Color, modifier: Modifier = Modifier) {
    val vector = remember(iconType, color) {
        val viewport = if (iconType.isPixelArt) 16f else 24f
        val fillType = if (iconType.isPixelArt) PathFillType.NonZero else PathFillType.EvenOdd
        
        ImageVector.Builder(
            name = "icon_${iconType.name}",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = viewport,
            viewportHeight = viewport
        ).addPath(
            pathData = addPathNodes(iconType.path),
            fill = if (iconType.isPixelArt) null else SolidColor(color),
            stroke = if (iconType.isPixelArt) SolidColor(color) else null,
            strokeLineWidth = if (iconType.isPixelArt) 1.5f else 0f,
            pathFillType = fillType
        ).build()
    }

    Icon(
        imageVector = vector,
        contentDescription = "Icon ${iconType.name}",
        modifier = modifier,
        tint = Color.Unspecified
    )
}
