package com.example.kncr.component.volume

import com.example.kncr.data.VolumeType
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class VolumeViewModelTest : FunSpec({
    test("initial volume is 0.7") {
        val viewModel = VolumeViewModel(VolumeType.Music)
        viewModel.volume.value.percent shouldBe 0.7f
    }

    test("handleOnMouseDrag sets volume relative to drag position") {
        val viewModel = VolumeViewModel(VolumeType.Music)
        viewModel.handleOnMouseDrag(0.3f)
        viewModel.volume.value.percent shouldBe 0.7f
    }

    test("handleOnMouseDrag with pos 0 sets volume to max") {
        val viewModel = VolumeViewModel(VolumeType.Music)
        viewModel.handleOnMouseDrag(0f)
        viewModel.volume.value.percent shouldBe 1.0f
    }

    test("handleOnMouseDrag with pos 1 sets volume to min") {
        val viewModel = VolumeViewModel(VolumeType.Music)
        viewModel.handleOnMouseDrag(1f)
        viewModel.volume.value.percent shouldBe 0.0f
    }

    context("handleOnScroll") {
        test("negative deltaY increases volume") {
            val viewModel = VolumeViewModel(VolumeType.Music)
            viewModel.handleOnScroll(-1f)
            viewModel.volume.value.percent shouldBe 0.8f
        }

        test("positive deltaY decreases volume") {
            val viewModel = VolumeViewModel(VolumeType.Music)
            viewModel.handleOnScroll(1f)
            viewModel.volume.value.percent shouldBe 0.6f
        }

        test("zero deltaY do nothing branch") {
            val viewModel = VolumeViewModel(VolumeType.Music)
            viewModel.handleOnScroll(0f)
            viewModel.volume.value.percent shouldBe 0.7f
        }
    }
})
