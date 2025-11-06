
plugins {
    scala
    java
    idea
}

repositories {
    mavenCentral()
}

dependencies {
    // Scala
    implementation("org.scala-lang:scala3-library_3:3.7.3")

    // Cucumber for Java
    testImplementation("io.cucumber:cucumber-java:7.14.0")
    testImplementation("io.cucumber:cucumber-junit:7.14.0")

    // JUnit
    testImplementation("junit:junit:4.13.2")

    // Optional: ScalaTest or other Scala testing frameworks
    //testImplementation("org.scalatest:scalatest_2.13:3.2.17")
    testImplementation("org.scalatest:scalatest_3:3.2.19")

    implementation("com.microsoft.playwright:playwright:1.53.0")
}

tasks.test {
    useJUnit()
}
