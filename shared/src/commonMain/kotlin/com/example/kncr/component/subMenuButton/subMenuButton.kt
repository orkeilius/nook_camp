package com.example.kncr.component.subMenuButton

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.unit.dp
import kncr.shared.generated.resources.Res
import kncr.shared.generated.resources.cog_icon
import kncr.shared.generated.resources.note_icon
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.imageResource


enum class SubMenuButtonType(val icon: DrawableResource, val altText: String) {
    Playlist(Res.drawable.note_icon, "Playlist"),
    Setting(Res.drawable.cog_icon, "Settings"),


}

@Composable
fun SubMenuButton(
    subMenuButtonType: SubMenuButtonType,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Image(
            bitmap = imageResource(subMenuButtonType.icon),
            contentDescription = subMenuButtonType.altText,
            modifier = Modifier.size(48.dp),
            filterQuality = FilterQuality.None
        )
    }
}