package com.example.kncr.data

import io.kotest.core.spec.style.FunSpec
import io.kotest.datatest.withData
import io.kotest.matchers.shouldBe

class VolumeUnitTest : FunSpec({

    context("constructor clamps input without rounding") {
        withData(
            0.0f to 0.0f,
            0.5f to 0.5f,
            1.0f to 1.0f,
            1.2f to 1.0f,
            -0.5f to 0.0f
        ) { (input, expected) ->
            VolumeUnit(input).percent shouldBe expected
        }
    }

    context("volumeUp adds step and clamps") {
        withData(
            0.0f to 0.1f,
            0.555f to 0.7f,
            0.95f to 1.0f,
            1.0f to 1.0f
        ) { (input, expected) ->
            VolumeUnit(input).volumeUp().percent shouldBe expected
        }
    }

    context("volumeDown subtracts step and clamps") {
        withData(
            0.0f to 0.0f,
            0.555f to 0.5f,
            0.95f to 0.9f,
            1.0f to 0.9f
        ) { (input, expected) ->
            VolumeUnit(input).volumeDown().percent shouldBe expected
        }
    }
})
