plugins {
    kotlin("jvm") version "2.0.0"

    // Provides the shadowJar task in Gradle
    // https://plugins.gradle.org/plugin/com.github.johnrengelman.shadow
    id("com.github.johnrengelman.shadow") version "8.1.1"

}

group = "com.yournamehere.montoya"
version = "0.1.0"

repositories {
    //add maven local in case you want to build some reusable libraries and host them within your home directory
    mavenLocal()

    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))

    // Include the Montoya API from Maven Central:
    // https://central.sonatype.com/artifact/net.portswigger.burp.extensions/montoya-api
    // Check for latest version: https://central.sonatype.com/artifact/net.portswigger.burp.extensions/montoya-api/versions
    implementation("net.portswigger.burp.extensions:montoya-api:2024.7")
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}