plugins {
    // this is necessary to avoid the plugins to be loaded multiple times
    // in each subproject's classloader
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.androidMultiplatformLibrary) apply false
    alias(libs.plugins.composeMultiplatform) apply false
    alias(libs.plugins.composeCompiler) apply false
    alias(libs.plugins.kotlinJvm) apply false
    alias(libs.plugins.kotlinMultiplatform) apply false
    id("org.sonarqube") version "7.3.0.8198"
}

sonar {
    properties {
        property("sonar.projectKey", "orkeilius_nook_camp")
        property("sonar.organization", "orkeilius")
    }
}