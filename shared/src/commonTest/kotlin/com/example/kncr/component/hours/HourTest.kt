package com.example.kncr.component.hours

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.v2.runComposeUiTest
import io.kotest.core.spec.style.FunSpec

@OptIn(ExperimentalTestApi::class)
class HourTest : FunSpec({

    test("displays current time text") {
        runComposeUiTest {
            val viewModel = HoursViewModel()
            setContent {
                HoursBoxComponent(viewModel)
            }

            onNodeWithText(viewModel.timeText.value).assertExists()
        }
    }

})
