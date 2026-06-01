package com.example.kncr.component.subMenuButton

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.v2.runComposeUiTest
import io.kotest.core.spec.style.FunSpec
import io.kotest.datatest.withData

@OptIn(ExperimentalTestApi::class)
class SubMenuButtonTest : FunSpec({

    context("displays alt text") {
        withData(
            SubMenuButtonType.Playlist,
            SubMenuButtonType.Setting,
            SubMenuButtonType.TownMusic,
        ) { type ->
            runComposeUiTest {
                setContent {
                    SubMenuButton(type)
                }

                onNodeWithContentDescription(type.altText).assertExists()
            }
        }
    }
})
