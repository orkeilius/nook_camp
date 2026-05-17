package com.example.kncr.component.hours

import com.example.kncr.utils.getCurrentTimeText
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.string.shouldMatch
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain

@OptIn(ExperimentalCoroutinesApi::class)
class HoursViewModelTest : FunSpec({
    val testDispatcher = StandardTestDispatcher()

    beforeSpec {
        Dispatchers.setMain(testDispatcher)
    }

    afterSpec {
        Dispatchers.resetMain()
    }

    test("initial timeText is in HH:MM format") {
        val viewModel = HoursViewModel()
        viewModel.timeText.value shouldMatch Regex("""^\d{2}:\d{2}$""")
    }

    test("initial timeText matches getCurrentTimeText") {
        val viewModel = HoursViewModel()
        viewModel.timeText.value shouldBe getCurrentTimeText()
    }
})
