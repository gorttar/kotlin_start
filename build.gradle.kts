import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

group = "org.gorttar"
version = "1.0-SNAPSHOT"

val kotlinVersion = "1.3.50"

plugins {
    java
    id("idea")
}

apply(plugin = "kotlin")

repositories { mavenCentral() }

dependencies {
    implementation("com.google.code.findbugs:jsr305:1.3.9")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:$kotlinVersion")
    implementation("org.jetbrains.kotlin:kotlin-reflect:$kotlinVersion")
    implementation("org.jetbrains.kotlin:kotlin-script-runtime:$kotlinVersion")
    implementation(project(":visibility"))
    testImplementation("org.testng:testng:6.13.1")
}

buildscript {
    repositories { mavenCentral() }
    dependencies {
        classpath("org.testng:testng:6.13.1")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.3.50")
    }
}

tasks {
    withType<JavaCompile> {
        sourceCompatibility = "1.8"
        targetCompatibility = "1.8"
    }

    withType<KotlinCompile> { kotlinOptions.jvmTarget = "1.8" }

    wrapper {
        gradleVersion = "5.2.1"
    }
}