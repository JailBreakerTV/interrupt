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

group = "eu.jailbreaker.connect"
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
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-jdk8:1.4.3")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.1.0")
    implementation("org.apache.ignite:ignite-core:2.10.0")
    implementation("org.apache.ignite:ignite-indexing:2.10.0")
}

configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_1_8
}