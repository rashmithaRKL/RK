plugins {
    alias(libs.plugins.shopping.kotlinMultiplatform)
    alias(libs.plugins.shopping.shared)
    alias(libs.plugins.ktlint)
}

ktlint {
    android = true
    outputToConsole = true
    outputColorName = "RED"
}

kotlin {
    sourceSets {
        commonTest {
            dependencies {
            }
        }
        commonMain {
            dependencies {
            }
        }

        androidMain {
            dependencies {
                implementation("io.coil-kt:coil-compose:2.2.2")
            }
        }
    }
}
