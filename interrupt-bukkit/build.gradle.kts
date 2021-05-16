import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

buildscript {
    repositories {
        maven("https://plugins.gradle.org/m2/")
    }
}

plugins {
    kotlin("jvm") version "1.4.31"
    kotlin("kapt") version "1.4.31"
    id("distribution")
    id("com.github.johnrengelman.shadow") version "6.1.0"
    id("net.linguica.maven-settings") version "0.5"
    id("org.hibernate.build.maven-repo-auth") version "3.0.2"
    `maven-publish`
}

group = "eu.jailbreaker.bukkit"
version = "0.0.1"

repositories {
    mavenLocal()
    mavenCentral()
    maven {
        name = "jcenter"
        url = uri("https://jcenter.bintray.com/")
    }
    maven {
        name = "jailbreaker-repo"
        url = uri("https://jailbreaker.eu/repositories")
    }
    maven {
        name = "sonatype-oss-snapshots"
        url = uri("https://oss.sonatype.org/content/repositories/snapshots/")
    }
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation(project(":interrupt-connect"))
    compileOnly("org.apache.ignite:ignite-core:2.10.0")
    compileOnly("org.apache.ignite:ignite-indexing:2.10.0")

    implementation("net.kyori:adventure-platform-bukkit:4.0.0-SNAPSHOT")
    implementation("org.reflections:reflections:0.9.12")

    compileOnly("org.spigotmc:plugin-annotations:1.2.3-SNAPSHOT")
    kapt("org.spigotmc:plugin-annotations:1.2.3-SNAPSHOT")
    compileOnly("org.spigotmc", "spigot", "1.16.4")
}

fun ShadowJar.relocate(pattern: String) {
    this.relocate(pattern, "${project.name}.$pattern")
}

tasks {
    named<ShadowJar>("shadowJar") {
        archiveFileName.set("InterruptBukkit.jar")
        destinationDirectory.set(file("C:\\Users\\Artillero\\Desktop\\Localhost_1_16\\1\\plugins"))
    }

    build {
        dependsOn(shadowJar)
    }

    withType<KotlinCompile> {
        kotlinOptions.jvmTarget = "1.8"
    }
}

configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_1_8
}