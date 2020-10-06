plugins {
    `java-gradle-plugin`
    `kotlin-dsl`
    java
    id("org.jetbrains.kotlin.jvm") version "1.3.61"
}

//apply(plugin = "maven-publish")

group = "com.saver12"
version = "1.0"
base.archivesBaseName = "telnot-plugin"

gradlePlugin {
    plugins {
        create("simplePlugin") {
            id = "telnot"
            implementationClass = "com.saver12.plugins.NotifierPlugin"
        }
    }
}

repositories {
//    mavenLocal()
    mavenCentral()
}

dependencies {
    implementation(group = "org.telegram", name = "telegrambots", version = "4.9.1")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
}