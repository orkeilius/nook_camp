package com.example.kncr.component.volume

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performTouchInput
import androidx.compose.ui.test.v2.runComposeUiTest
import com.example.kncr.data.VolumeType
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

@OptIn(ExperimentalTestApi::class)
class VolumeComponentTest : FunSpec({

    test("displays Music icon when VolumeType is Music") {
        runComposeUiTest {
            val viewModel = VolumeViewModel(VolumeType.Music)
            setContent {
                VolumeComponent(viewModel)
            }

            onNodeWithContentDescription("Icon Music").assertExists()
        }
    }

    test("displays Rain icon when VolumeType is Rain") {
        runComposeUiTest {
            val viewModel = VolumeViewModel(VolumeType.Rain)
            setContent {
                VolumeComponent(viewModel)
            }

            onNodeWithContentDescription("Icon Rain").assertExists()
        }
    }

    test("drag at top of volume bar sets volume to max") {
        runComposeUiTest {
            val viewModel = VolumeViewModel(VolumeType.Music)
            setContent {
                VolumeComponent(viewModel)
            }

            onNodeWithTag("volumeBar").performTouchInput {
                down(Offset(10f, 0f))
                up()
            }

            viewModel.volume.value.percent shouldBe 1.0f
        }
    }

    test("drag at bottom of volume bar sets volume to min") {
        runComposeUiTest {
            val viewModel = VolumeViewModel(VolumeType.Music)
            setContent {
                VolumeComponent(viewModel)
            }

            onNodeWithTag("volumeBar").performTouchInput {
                down(Offset(10f, 115f))
                up()
            }

            viewModel.volume.value.percent shouldBe 0.0f
        }
    }

    test("drag across volume bar updates volume continuously") {
        runComposeUiTest {
            val viewModel = VolumeViewModel(VolumeType.Music)
            setContent {
                VolumeComponent(viewModel)
            }

            onNodeWithTag("volumeBar").performTouchInput {
                down(Offset(10f, 0f))
                moveTo(Offset(10f, 20f))
                up()
            }

            viewModel.volume.value.percent shouldBe 0.8f
        }
    }

    test("scroll up increases volume via viewModel") {
        runComposeUiTest {
            val viewModel = VolumeViewModel(VolumeType.Music)
            setContent {
                VolumeComponent(viewModel)
            }

            viewModel.handleOnScroll(-1f)

            viewModel.volume.value.percent shouldBe 0.8f
        }
    }

    test("scroll down decreases volume via viewModel") {
        runComposeUiTest {
            val viewModel = VolumeViewModel(VolumeType.Music)
            setContent {
                VolumeComponent(viewModel)
            }

            viewModel.handleOnScroll(1f)

            viewModel.volume.value.percent shouldBe 0.6f
        }
    }
})
