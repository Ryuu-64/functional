plugins {
    `java-library`
    id("me.champeau.jmh") version "0.7.3"
}

group = "org.ryuu"
version = "6.0.0"

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(8))
    }
}

repositories {
    mavenCentral()
}

dependencies {
    // https://mvnrepository.com/artifact/org.junit.jupiter/junit-jupiter-api
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.13.4")
    // https://mvnrepository.com/artifact/org.junit.jupiter/junit-jupiter-params (for parameterized tests)
    testImplementation("org.junit.jupiter:junit-jupiter-params:5.13.4")
    // https://mvnrepository.com/artifact/org.junit.jupiter/junit-jupiter-engine
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.13.4")
    // https://mvnrepository.com/artifact/org.junit.platform/junit-platform-launcher
    testImplementation("org.junit.platform:junit-platform-launcher:1.13.4")

    // https://mvnrepository.com/artifact/org.assertj/assertj-core
    testImplementation("org.assertj:assertj-core:3.27.7")

    // https://mvnrepository.com/artifact/org.openjdk.jmh/jmh-core
    testImplementation("org.openjdk.jmh:jmh-core:1.37")
    // https://mvnrepository.com/artifact/org.openjdk.jmh/jmh-generator-annprocess
    testAnnotationProcessor("org.openjdk.jmh:jmh-generator-annprocess:1.37")
}

tasks.test {
    useJUnitPlatform()
    // Fix Chinese encoding issues in Java 8
    jvmArgs("-Dfile.encoding=UTF-8")
}

tasks.compileTestJava {
    options.encoding = "UTF-8"
}

jmh {
    profilers.set(listOf("gc", "stack", "jfr"))
}
