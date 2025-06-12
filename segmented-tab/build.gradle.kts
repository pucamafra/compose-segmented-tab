plugins {
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.android.library)
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.gradle.ktlint)
}

apply {
    from("../lint.gradle")
}

android {
    namespace = "com.marlonmafra.segmentedTab"
    compileSdk = 35

    defaultConfig {
        minSdk = 24
        testOptions.targetSdk = 35
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
}

dependencies {
    val composeBom = platform(libs.androidx.compose.bom)
    implementation(composeBom)

    implementation(libs.androidx.material3.android)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)

    debugImplementation(libs.androidx.ui.tooling.preview.android)
    debugImplementation(libs.androidx.ui.tooling)
}
