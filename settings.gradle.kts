pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.PREFER_SETTINGS) // you may also set it to PREFER_PROJECT to avoid the warning
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "JetWeather"
include(":app")
include(":features")
include(":features:currentweather")
include(":core")
