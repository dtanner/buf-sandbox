rootProject.name = "buf-sandbox"

pluginManagement {
    repositories {
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    @Suppress("UnstableApiUsage")
    repositories {
        mavenLocal()
        mavenCentral()
    }
}

buildscript {
    repositories {
        gradlePluginPortal()
    }
}
