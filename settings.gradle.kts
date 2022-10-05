import groovy.lang.MissingPropertyException

pluginManagement {
    repositories {
        // Default repositories
        gradlePluginPortal()
        mavenCentral()
        mavenLocal()

        // Repositories
        maven("https://maven.fabricmc.net")
        maven("https://maven.architectury.dev/")
        maven("https://maven.minecraftforge.net")
        maven("https://repo.essential.gg/repository/maven-public")
        maven("https://server.bbkr.space/artifactory/libs-release/")
        maven("https://jitpack.io/")
        maven("https://maven.unifycraft.xyz/releases")

        // Snapshots
        maven("https://maven.unifycraft.xyz/snapshots")
        maven("https://s01.oss.sonatype.org/content/groups/public/")
    }
}

rootProject.name = extra["mod.name"]?.toString()
    ?: throw MissingPropertyException("mod.name has not been set.")
