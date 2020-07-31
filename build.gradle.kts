group = "org.gorttar"
version = "1.0-SNAPSHOT"

val kotlinGroup = "org.jetbrains.kotlin"
val javaLangVersion = "1.8"
val kotlinLangVersion = "1.3.72"

plugins {
    java
    id("idea")
    kotlin("jvm") version "1.3.72"
}

repositories { mavenCentral() }

dependencies {
    implementation(group = "com.google.code.findbugs", name = "jsr305", version = "1.3.9")
    implementation(group = kotlinGroup, name = "kotlin-stdlib-jdk8", version = kotlinLangVersion)
    implementation(group = kotlinGroup, name = "kotlin-reflect", version = kotlinLangVersion)
    implementation(group = kotlinGroup, name = "kotlin-script-runtime", version = kotlinLangVersion)
    implementation(group = kotlinGroup, name = "kotlin-test", version = kotlinLangVersion)
    implementation(group = "org.jetbrains.kotlinx", name = "kotlinx-coroutines-core", version = "1.3.5")
    implementation(group = "com.github.ajalt", name = "mordant", version = "1.2.1")
    implementation(group = "com.nhaarman", name = "mockito-kotlin", version = "1.6.0")
    implementation(group = "com.opencsv", name = "opencsv", version = "5.0")
    implementation(group = "com.willowtreeapps.assertk", name = "assertk-jvm", version = "0.20")

    implementation(project(":visibility"))

    testImplementation(group = "org.junit.jupiter", name = "junit-jupiter-api", version = "5.6.2")
    testRuntimeOnly(group = "org.junit.jupiter", name = "junit-jupiter-engine", version = "5.6.2")
    implementation(kotlin("stdlib-jdk8"))
}

buildscript {
    repositories { mavenCentral() }
    dependencies {
        classpath(group = "org.junit.jupiter", name = "junit-jupiter-api", version = "5.6.2")
        classpath(group = "org.jetbrains.kotlin", name = "kotlin-gradle-plugin", version = "1.3.72")
    }
}

tasks {
    test { useJUnitPlatform() }

    listOf(compileJava, compileTestJava).forEach {
        it {
            sourceCompatibility = javaLangVersion
            targetCompatibility = javaLangVersion
        }
    }

    listOf(compileKotlin, compileTestKotlin).forEach {
        it {
            kotlinOptions {
                jvmTarget = javaLangVersion
            }
        }
    }

    wrapper { gradleVersion = "6.5.1" }
}