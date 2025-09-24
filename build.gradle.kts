plugins {
    kotlin("jvm") version "2.0.21"
}

group = "ie.setu"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    implementation("org.slf4j:slf4j-simple:2.0.16")
    implementation("io.github.microutils:kotlin-logging:3.0.5")

}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}
