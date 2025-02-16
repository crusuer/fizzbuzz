plugins {
    id("java")
    id("org.springframework.boot") version "3.4.2"
    id("io.spring.dependency-management") version "1.1.7"
    id ("io.gitlab.arturbosch.detekt") version "1.23.7"
    kotlin("jvm") version "2.0.10"
}

group = "fr.leboncoin.fizzbuzz"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

detekt {
    buildUponDefaultConfig = true
    allRules = false
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

dependencies {
    val junitVersion = "5.10.0"

    implementation("org.springframework.boot:spring-boot-starter")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.junit.jupiter:junit-jupiter:$junitVersion")
}

tasks.test {
    useJUnitPlatform()
}