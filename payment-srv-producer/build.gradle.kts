plugins {
    kotlin("jvm") version "2.3.21"
    kotlin("plugin.spring") version "2.3.21"
    id("org.springframework.boot") version "4.1.1"
    id("io.spring.dependency-management") version "1.1.7"
    id("com.bakdata.avro") version "2.0.0"
}

group = "com.clau"
version = "0.0.1-SNAPSHOT"
description = "payment-srv-producer"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(25)
    }
}

repositories {
    mavenCentral()
    // O KafkaAvroSerializer/Deserializer da Confluent NÃO está no Maven Central.
    maven { url = uri("https://packages.confluent.io/maven/") }
}

extra["datasourceMicrometerVersion"] = "2.2.1"
extra["confluentVersion"] = "7.9.1"
extra["avroVersion"] = "1.12.0"

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-kafka")
    implementation("org.springframework.boot:spring-boot-starter-webmvc")
    implementation("org.apache.avro:avro:${property("avroVersion")}")
    implementation("io.confluent:kafka-avro-serializer:${property("confluentVersion")}")
    implementation("net.ttddyy.observation:datasource-micrometer-spring-boot")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("tools.jackson.module:jackson-module-kotlin")
    compileOnly("org.projectlombok:lombok")
    annotationProcessor("org.projectlombok:lombok")
    testImplementation("org.springframework.boot:spring-boot-starter-kafka-test")
    testImplementation("org.springframework.boot:spring-boot-starter-webmvc-test")
    testImplementation("org.springframework.boot:spring-boot-testcontainers")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
    testImplementation("org.testcontainers:testcontainers-junit-jupiter")
    testImplementation("org.testcontainers:testcontainers-kafka")
    testCompileOnly("org.projectlombok:lombok")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    testAnnotationProcessor("org.projectlombok:lombok")
}

dependencyManagement {
    imports {
        mavenBom("net.ttddyy.observation:datasource-micrometer-bom:${property("datasourceMicrometerVersion")}")
    }
}

kotlin {
    compilerOptions {
        freeCompilerArgs.addAll("-Xjsr305=strict", "-Xannotation-default-target=param-property")
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}
