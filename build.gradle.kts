plugins {
    // Provides Kotlin Language Support
    // https://plugins.gradle.org/plugin/org.jetbrains.kotlin.jvm
    kotlin("jvm") version "2.3.21"

    // Provides the shadowJar task in Gradle
    // https://plugins.gradle.org/plugin/com.gradleup.shadow
    id("com.gradleup.shadow") version "9.4.1"

}

//Change this to reflect your package namespace
group = "com.yournamehere.montoya"
version = "0.1.0"

repositories {
    //add maven local in case you want to build some reusable libraries and host them within your home directory
    mavenLocal()
    mavenCentral()


    maven(url="https://jitpack.io") {
        content {
            includeGroup("com.github.ncoblentz")
        }
    }

}

dependencies {
    testImplementation(kotlin("test"))

    // Include the Montoya API from Maven Central:
    // https://central.sonatype.com/artifact/net.portswigger.burp.extensions/montoya-api
    // Check for latest version: https://central.sonatype.com/artifact/net.portswigger.burp.extensions/montoya-api/versions
    implementation("net.portswigger.burp.extensions:montoya-api:2026.4")

    // Enable these if you want to use https://github.com/ncoblentz/BurpMontoyaLibrary
    implementation("com.github.ncoblentz:BurpMontoyaLibrary:0.2.0")
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(25)
}