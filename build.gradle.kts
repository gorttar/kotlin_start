import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

group = "org.gorttar"
version = "1.0-SNAPSHOT"

val kotlinGroup = "org.jetbrains.kotlin"
val kotlinVersion = "1.3.72"

plugins {
    java
    id("idea")
}

apply(plugin = "kotlin")

repositories { mavenCentral() }

dependencies {
    implementation(group = "com.google.code.findbugs", name = "jsr305", version = "1.3.9")
    implementation(group = kotlinGroup, name = "kotlin-stdlib-jdk8", version = kotlinVersion)
    implementation(group = kotlinGroup, name = "kotlin-reflect", version = kotlinVersion)
    implementation(group = kotlinGroup, name = "kotlin-script-runtime", version = kotlinVersion)
    implementation(group = kotlinGroup, name = "kotlin-test", version = kotlinVersion)
    implementation(group = "org.jetbrains.kotlinx", name = "kotlinx-coroutines-core", version = "1.3.5")
    implementation(group = "com.github.ajalt", name = "mordant", version = "1.2.1")
    implementation(group = "com.nhaarman", name = "mockito-kotlin", version = "1.6.0")
    implementation(group = "com.opencsv", name = "opencsv", version = "5.0")
    implementation(group = "com.willowtreeapps.assertk", name = "assertk-jvm", version = "0.20")

    implementation(project(":visibility"))

    testImplementation(group = "org.testng", name = "testng", version = "6.14.3")
    testImplementation(group = "com.willowtreeapps.assertk", name = "assertk-jvm", version = "0.20")
}

buildscript {
    repositories { mavenCentral() }
    dependencies {
        classpath(group = "org.testng", name = "testng", version = "6.14.3")
        classpath(group = "org.jetbrains.kotlin", name = "kotlin-gradle-plugin", version = "1.3.50")
    }
}

tasks {
    withType<JavaCompile> {
        sourceCompatibility = "1.8"
        targetCompatibility = "1.8"
    }
    withType<Test> { useTestNG() }
    withType<KotlinCompile> { kotlinOptions.jvmTarget = "1.8" }
    wrapper { gradleVersion = "5.2.1" }
}