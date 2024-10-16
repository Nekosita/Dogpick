// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
}
val koinAndroidxViewmodelVersion by extra("3.4.3")
val koinAndroidxViewmodelVersion1 by extra(koinAndroidxViewmodelVersion)
