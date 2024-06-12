group = "org.poding84.example"
version = "0.0.1-SNAPSHOT"

plugins {
    id("org.springframework.boot") version "3.1.4"
    id("io.spring.dependency-management") version "1.1.3"
    kotlin("plugin.spring") version "1.8.22"
    kotlin("jvm") version "1.8.22"
}
val springVersion = "3.1.4"
val kotlinxCoroutinesVersion = "1.7.3"
val springCloudVersion = "2022.0.3"

java.sourceCompatibility = JavaVersion.VERSION_17

repositories {
    mavenCentral()
}

dependencyManagement {
    imports {
        mavenBom("org.springframework.cloud:spring-cloud-dependencies:$springCloudVersion")
    }
}

dependencies {
    // Spring
    implementation("org.springframework.boot:spring-boot-starter")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-webflux")

    // Kotlin
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    api("org.jetbrains.kotlinx:kotlinx-coroutines-core:$kotlinxCoroutinesVersion")
    api("org.jetbrains.kotlinx:kotlinx-coroutines-core-jvm:$kotlinxCoroutinesVersion")
    api("org.jetbrains.kotlinx:kotlinx-coroutines-reactive:$kotlinxCoroutinesVersion")
    api("org.jetbrains.kotlinx:kotlinx-coroutines-reactor:$kotlinxCoroutinesVersion")
    api("aws.sdk.kotlin:dynamodb:1.0.0")

}
