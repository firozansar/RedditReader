repositories {
    google()
    mavenCentral()
    gradlePluginPortal()
}

plugins {
    kotlin("android")
    kotlin("kapt")

    id("com.android.application")
    id("dagger.hilt.android.plugin")
    id("org.jetbrains.kotlin.plugin.parcelize")
    id("androidx.navigation.safeargs.kotlin")
    id("io.gitlab.arturbosch.detekt") version "1.21.0"
}

android {
    compileSdk = libs.versions.compileSdk.get().toInt()

    defaultConfig {
        applicationId = "info.firozansari.redditreader"

        minSdk = libs.versions.minSdk.get().toInt()
        targetSdk = libs.versions.targetSdk.get().toInt()

        versionCode = 2
        versionName = "1.1"

        buildConfigField("String", "BASE_API_URL", "\"https://www.reddit.com/\"")

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        // The following argument makes the Android Test Orchestrator run its
        // "pm clear" command after each test invocation. This command ensures
        // that the app's state is completely cleared between tests.
        testInstrumentationRunnerArguments["clearPackageData"] = "true"

        //multiDexEnabled = true
    }

    testOptions {
        unitTests {
            isIncludeAndroidResources = true
        }
        unitTests.all { it.useJUnitPlatform() }
        execution = "ANDROIDX_TEST_ORCHESTRATOR"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
        getByName("debug") {
            isMinifyEnabled = false
        }
    }

    compileOptions {
        // Desugar Java 8+ https://developer.android.com/studio/write/java8-support#library-desugaring
        isCoreLibraryDesugaringEnabled = true

        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }

    useLibrary("android.test.runner")
    useLibrary("android.test.base")
    useLibrary("android.test.mock")

    // Required to run Espresso/Integration tests
    packagingOptions {
        resources.excludes += "META-INF/LICENSE.md"
        resources.excludes += "META-INF/LICENSE-notice.md"
        resources.excludes += "META-INF/AL2.0"
        resources.excludes += "META-INF/LGPL2.1"
        resources.excludes += "META-INF/gradle/incremental.annotation.processors"
    }
    configurations {
        androidTestImplementation {
            exclude(group = "io.mockk", module = "mockk-agent-jvm")
        }
    }
}

dependencies {

    coreLibraryDesugaring(libs.desugarJdk)

    implementation(libs.coroutines.core)
    implementation(libs.coroutines.rx3)

    // Moshi
    implementation(libs.moshi)
    implementation(libs.moshiCodeGen)

    // Dagger
    implementation(libs.dagger.hilt.runtime)
    kapt(libs.dagger.hilt.compiler)

    // RxJava3
    implementation(libs.rxJava)
    implementation(libs.rxAndroid)

    // Unit test  dependencies
    testImplementation(libs.mockK)
    testRuntimeOnly(libs.junitVintageEngine)
    testImplementation(libs.junit5Api)
    testRuntimeOnly(libs.junit5Engine)
    testImplementation(libs.junit5Params)
    testImplementation(libs.extJUnit)
    testImplementation(libs.kotlinxCoroutinesTest)

    // Android test dependencies
    androidTestImplementation(libs.espressoCore)
    androidTestImplementation(libs.androidTestMonitor)
    androidTestImplementation(libs.kotlinxCoroutinesTest)
    androidTestImplementation(libs.mockKAndroid)
    androidTestImplementation(libs.mockKAgentJvm)
    androidTestImplementation(libs.androidTestRunner)
    androidTestUtil(libs.androidTestOrchestrator)

    androidTestImplementation(libs.dagger.runtime)
    kaptAndroidTest(libs.dagger.compiler)
}

tasks.withType<Test> {
    useJUnitPlatform()
}
