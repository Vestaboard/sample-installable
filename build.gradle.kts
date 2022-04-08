import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
  kotlin("jvm") version "1.5.10"
  id("com.github.johnrengelman.shadow") version "4.0.4"
}

group = "com.vestaboard.installables.sample"
version = "1.0-SNAPSHOT"

repositories {
  mavenCentral()
}

val exposedVersion = "0.37.3"

dependencies {
  testImplementation(kotlin("test"))
  implementation("com.squareup.okhttp3:okhttp:4.9.3")
  implementation("org.json:json:20211205")
  implementation("io.javalin:javalin:4.1.1")
  implementation("com.google.guava:guava:26.0-jre")
  implementation("org.slf4j:slf4j-simple:1.8.0-beta4")
  implementation("org.jetbrains.exposed:exposed-core:$exposedVersion")
  implementation("org.jetbrains.exposed:exposed-dao:$exposedVersion")
  implementation("org.jetbrains.exposed:exposed-jdbc:$exposedVersion")
  implementation("org.jetbrains.exposed:exposed-java-time:$exposedVersion")
  implementation("mysql:mysql-connector-java:8.0.11")
  implementation("com.fasterxml.jackson.core:jackson-databind:2.12.4")
  implementation("commons-codec:commons-codec:1.15")
}

tasks.test {
  useJUnit()
}

tasks.withType<KotlinCompile>() {
  kotlinOptions.jvmTarget = "1.8"
}

tasks {
  named<com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar>("shadowJar") {
    archiveBaseName.set("shadow")
    mergeServiceFiles()
    manifest {
      attributes(mapOf("Main-Class" to "com.vestaboard.installables.sample.MainKt"))
    }
  }
}

tasks {
  build {
    dependsOn(shadowJar)
  }
}