import build.buf.gradle.GENERATED_DIR

plugins {
    kotlin("jvm") version libs.versions.kotlin.core
    `java-test-fixtures`
    alias(libs.plugins.buf)
}

kotlin {
    jvmToolchain(17)
    compilerOptions { allWarningsAsErrors = true }
}

tasks {
    withType<Test> {
        useJUnitPlatform()
    }
}

dependencies {
    api(libs.bundles.protobuf)
    testImplementation(libs.bundles.testing)
}

// not necessary for a single-module project with the files at project root, but shows how you can specify the path
// if the files are in a different location, for example a monorepo subdirectory
buf {
    configFileLocation = project.file("buf.yaml")
    generate {
        templateFileLocation = project.file("buf.gen.yaml")
    }
}

tasks {
    compileJava.get().dependsOn(bufGenerate)
    compileKotlin.get().dependsOn(bufGenerate)
}

sourceSets {
    main {
        java.srcDir(layout.buildDirectory.file("bufbuild/$GENERATED_DIR/java"))
        kotlin.srcDir(layout.buildDirectory.file("bufbuild/$GENERATED_DIR/kotlin"))
    }
}

// todo build in a prerequisite to call `buf dep update` before `buf generate` to ensure the dependencies are up to date
// https://github.com/bufbuild/buf-gradle-plugin#generating-dependencies


