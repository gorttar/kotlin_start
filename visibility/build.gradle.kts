group = "org.gorttar"
version = "1.0-SNAPSHOT"

val kotlinGroup = "org.jetbrains.kotlin"
val javaLangVersion = "1.8"
val kotlinLangVersion = "1.3.72"

plugins {
    java
    id("idea")
    kotlin("jvm")
}

repositories { mavenCentral() }

dependencies {
    implementation("com.google.code.findbugs:jsr305:1.3.9")
    implementation(group = kotlinGroup, name = "kotlin-stdlib-jdk8", version = kotlinLangVersion)
    implementation(group = kotlinGroup, name = "kotlin-reflect", version = kotlinLangVersion)
    implementation(group = kotlinGroup, name = "kotlin-script-runtime", version = kotlinLangVersion)
}

buildscript {
    repositories { mavenCentral() }
    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.3.72")
    }
}

tasks {
    test {
        useTestNG()
    }

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
}