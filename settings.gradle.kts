pluginManagement {
    repositories {
        google()
        maven (url="https://jitpack.io" )
        maven (url="https://oss.sonatype.org/content/repositories/snapshots/" )
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        maven (url="https://jitpack.io" )
        mavenCentral()
    }
}

rootProject.name = "PopCorn"
include(":app")
