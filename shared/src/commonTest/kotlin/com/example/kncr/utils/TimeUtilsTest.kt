package com.example.kncr.utils

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.string.shouldMatch

class TimeUtilsTest : FunSpec({
    test("getCurrentTimeText returns HH:MM format") {
        getCurrentTimeText() shouldMatch Regex("""^\d{2}:\d{2}$""")
    }

    test("getCurrentTimeText hour is between 00 and 23") {
        val hour = getCurrentTimeText().substring(0, 2).toInt()
        (hour in 0..23) shouldBe true
    }

    test("getCurrentTimeText minute is between 00 and 59") {
        val minute = getCurrentTimeText().substring(3, 5).toInt()
        (minute in 0..59) shouldBe true
    }

    test("getMillisToNextMinute returns a positive value") {
        (getMillisToNextMinute() > 0) shouldBe true
    }

    test("getMillisToNextMinute returns at most 61000") {
        (getMillisToNextMinute() <= 61_000L) shouldBe true
    }
})
