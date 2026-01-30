// build.gradle.kts
plugins {
    java
    application
}

group = "com.abnormal.tools"
version = "1.4.3"

repositories {
    mavenCentral()
    maven {
        name = "TarsosDSP repository"
        url = uri("https://mvn.0110.be/releases")
    }
}

dependencies {
    implementation("net.java.dev.jna:jna:5.13.0")
    implementation("com.googlecode.soundlibs:mp3spi:1.9.5.4")

    testImplementation("junit:junit:4.13.1")
    testImplementation("org.hamcrest:hamcrest-core:1.3")
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
}
tasks {
    compileJava {
        options.encoding = "UTF-8"
    }
}