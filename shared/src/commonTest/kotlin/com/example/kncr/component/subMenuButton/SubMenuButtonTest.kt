package com.example.kncr.component.subMenuButton

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.v2.runComposeUiTest
import io.kotest.core.spec.style.FunSpec

@OptIn(ExperimentalTestApi::class)
class SubMenuButtonTest : FunSpec({

    test("displays Playlist alt text when SubMenuButtonType is Playlist") {
        runComposeUiTest {
            setContent {
                SubMenuButton(SubMenuButtonType.Playlist)
            }

            onNodeWithContentDescription("Playlist").assertExists()
        }
    }

    test("displays Settings alt text when SubMenuButtonType is Setting") {
        runComposeUiTest {
            setContent {
                SubMenuButton(SubMenuButtonType.Setting)
            }

            onNodeWithContentDescription("Settings").assertExists()
        }
    }
})
