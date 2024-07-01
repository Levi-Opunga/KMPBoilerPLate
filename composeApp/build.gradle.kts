import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.targets.js.dsl.ExperimentalWasmDsl
import org.jetbrains.kotlin.gradle.targets.js.webpack.KotlinWebpackConfig

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsCompose)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.kotlinx.serialization)
    id("levis-dependency-management")
}

kotlin {
    @OptIn(ExperimentalWasmDsl::class)
    wasmJs {
        moduleName = "composeApp"
        browser {
            commonWebpackConfig {
                outputFileName = "composeApp.js"
                devServer = (devServer ?: KotlinWebpackConfig.DevServer()).apply {
                    static = (static ?: mutableListOf()).apply {
                        // Serve sources to debug inside browser
                        add(project.projectDir.path)
                    }
                }
            }
        }
        binaries.executable()
    }
    
    androidTarget {
        @OptIn(ExperimentalKotlinGradlePluginApi::class)
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_11)
        }
    }
    
    jvm("desktop")
    
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "ComposeApp"
            isStatic = true
        }
    }
    
    sourceSets {
        val desktopMain by getting
        
        androidMain.dependencies {
            implementation(compose.preview)
            implementation(libs.androidx.activity.compose)
            // dependency injection
            implementation(libs.voyager.koin)
            implementation(libs.koin.android)

            // coroutines
            implementation(libs.koin.core.coroutines)
            implementation(libs.kotlinx.coroutines.android)

            // network
            implementation(libs.ktor.client.okhttp)


        }
        iosMain.dependencies {
            implementation(libs.voyager.koin)
            implementation(libs.koin.core.coroutines)
            implementation(libs.ktor.client.darwin)
        }
        wasmJsMain.dependencies {
//            implementation(libs.voyager.koin)
//            implementation(libs.ktor.client.js)
        }
        commonMain.dependencies {
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material)
            implementation(compose.material3)
            implementation(compose.ui)
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)
            // navigation
            implementation(libs.voyager.navigator)
            implementation(libs.voyager.transitions)
            implementation(libs.voyager.lifecycle.kmp)
            implementation(libs.voyager.screenModel)
            // dependency injection
            implementation(project.dependencies.platform(libs.koin.bom))
            implementation(libs.koin.core)
            implementation(libs.koin.compose)
            // coroutines
            implementation(libs.kotlinx.coroutines.core)

            // networking and serialization with ktor
            implementation(libs.bundles.ktor.common)

//            implementation(libs.ktor.client.cio)

//            implementation(libs.ktor.client.core)
//            implementation(libs.ktor.client.json)
//            implementation(libs.ktor.client.logging)
//            implementation(libs.ktor.client.serialization)
//            implementation(libs.ktor.client.content.negotiation)
//            implementation(libs.ktor.serialization.kotlinx.json)

            // components
            implementation(libs.sonner)
            implementation(libs.window.chrisbanes)

        }
        desktopMain.dependencies {
            implementation(compose.desktop.currentOs)
            implementation(libs.voyager.koin)
            implementation(libs.koin.core.coroutines)
            implementation(libs.kotlinx.coroutines.swing)
            implementation(libs.ktor.client.java)
        }
    }
}

dependencyVersions{
    ktor = "2.3.12"

}

android {
    namespace = "dev.levi"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    sourceSets["main"].res.srcDirs("src/androidMain/res")
    sourceSets["main"].resources.srcDirs("src/commonMain/resources")

    defaultConfig {
        applicationId = "dev.levi"
        minSdk = libs.versions.android.minSdk.get().toInt()
        targetSdk = libs.versions.android.targetSdk.get().toInt()
        versionCode = 1
        versionName = "1.0"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    buildFeatures {
        compose = true
    }
    dependencies {
        debugImplementation(compose.uiTooling)
    }
}

compose.desktop {
    application {
        mainClass = "MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "dev.levi"
            packageVersion = "1.0.0"
        }
    }
}
