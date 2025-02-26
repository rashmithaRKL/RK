plugins {
    `kotlin-dsl`
}

group = "com.razzaghi.rkshopping.buildlogic"

dependencies {
    compileOnly(libs.plugins.kotlin.serialization.toDep())
    compileOnly(libs.plugins.androidApplication.toDep())
    compileOnly(libs.plugins.androidLibrary.toDep())
    compileOnly(libs.plugins.composeMultiplatform.toDep())
    compileOnly(libs.plugins.kotlinMultiplatform.toDep())
    compileOnly(libs.plugins.compose.compiler.toDep())
}

fun Provider<PluginDependency>.toDep() = map {
    "${it.pluginId}:${it.pluginId}.gradle.plugin:${it.version}"
}

tasks {
    validatePlugins {
        enableStricterValidation = true
        failOnWarning = true
    }
}

gradlePlugin {
    plugins {
        register("kotlinMultiplatform") {
            id = "com.razzaghi.rkshopping.kotlinMultiplatform"
            implementationClass = "KotlinMultiplatformConventionPlugin"
        }
        register("shared") {
            id = "com.razzaghi.rkshopping.shared"
            implementationClass = "SharedConventionPlugin"
        }
        register("androidApp") {
            id = "com.razzaghi.rkshopping.androidApp"
            implementationClass = "AndroidAppConventionPlugin"
        }
    }
}