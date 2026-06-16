import org.gradle.kotlin.dsl.dependencies
import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidMultiplatformLibrary)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.kover)
    alias(libs.plugins.osdetector)
}

kover {
    currentProject {
        sources {
            excludedSourceSets.addAll("commonTest", "jvmTest")
        }
    }
}

kotlin {
    jvm {
        testRuns.all {
            executionTask {
                useJUnitPlatform()
            }
        }
    }

    @OptIn(ExperimentalWasmDsl::class)
    wasmJs {
        browser()
    }

    androidLibrary {
        namespace = "com.example.kncr.shared"
        compileSdk = libs.versions.android.compileSdk.get().toInt()
        minSdk = libs.versions.android.minSdk.get().toInt()

        compilerOptions {
            jvmTarget = JvmTarget.JVM_21
        }
        androidResources {
            enable = true
        }
        withHostTest {
            isIncludeAndroidResources = true
        }
    }

    sourceSets {
        androidMain.dependencies {
            implementation(libs.compose.uiToolingPreview)
        }
        commonMain.dependencies {
            implementation(libs.compose.runtime)
            implementation(libs.compose.foundation)
            implementation(libs.compose.material3)
            implementation(libs.compose.ui)
            implementation(libs.compose.components.resources)
            implementation(libs.compose.uiToolingPreview)
            implementation(libs.androidx.lifecycle.viewmodelCompose)
            implementation(libs.androidx.lifecycle.runtimeCompose)
            implementation(libs.kotlinx.datetime)
            implementation(libs.gadulka)
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
            implementation(libs.kotest.framework.engine)
            implementation(libs.kotest.assertions.core)
            implementation(libs.kotlinx.coroutines.test)
            implementation(libs.ui.test)
        }
        jsMain.dependencies {
            implementation(libs.wrappers.browser)
        }
        jvmTest.dependencies {
            implementation(libs.kotest.runner.junit6)
            implementation(compose.desktop.currentOs)
        }

        val jvmMain by getting {
            dependencies {
                val fxSuffix = when (osdetector.classifier) {
                    "linux-x86_64" -> "linux"
                    "linux-aarch_64" -> "linux-aarch64"
                    "windows-x86_64" -> "win"
                    "osx-x86_64" -> "mac"
                    "osx-aarch_64" -> "mac-aarch64"
                    else -> throw IllegalStateException("Unknown OS: ${osdetector.classifier}")
                }
                implementation("org.openjfx:javafx-base:${libs.versions.javafx.get()}:${fxSuffix}")
                implementation("org.openjfx:javafx-graphics:${libs.versions.javafx.get()}:${fxSuffix}")
                implementation("org.openjfx:javafx-controls:${libs.versions.javafx.get()}:${fxSuffix}")
                implementation("org.openjfx:javafx-swing:${libs.versions.javafx.get()}:${fxSuffix}")
                implementation("org.openjfx:javafx-web:${libs.versions.javafx.get()}:${fxSuffix}")
                implementation("org.openjfx:javafx-media:${libs.versions.javafx.get()}:${fxSuffix}")
            }
        }
    }
}

dependencies {
    androidRuntimeClasspath(libs.compose.uiTooling)
}