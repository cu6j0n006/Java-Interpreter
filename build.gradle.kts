plugins {
    id("java")
}

group = "fr.univ_amu.l3mi"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}

// Define a task to run the GenerateAST tool
tasks.register<JavaExec>("generateAst") {
    classpath = sourceSets.main.get().runtimeClasspath
    mainClass.set("tool.GenerateAST")
    args("src/main/java/ZubaLang")
}