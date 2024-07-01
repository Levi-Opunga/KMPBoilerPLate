interface DependencyVersions {
    val kotlin : Property<String>
    val voyager : Property<String>
    val koin : Property<String>
    val kotlinxCoroutines : Property<String>
    val sonner : Property<String>
    val windowChrisbanes : Property<String>
    val ktor : Property<String>
}

val dependencyExtension = project.extensions.create<DependencyVersions>("dependencyVersions")

the<DependencyVersions>().apply {
    kotlin.set("2.0.0")
    kotlinxCoroutines.set("1.5.2")
    koin.set("3.1.2")
    voyager.set("1.1.0-beta02")
    sonner.set("0.3.6")
    windowChrisbanes.set("0.5.0")
    ktor.set("2.3.12")
}




dependencies{
    add("implementation", "io.github.dokar3:sonner:${dependencyExtension.sonner.get()}")
}

tasks.register("printDependencies") {
    doLast {
        println("Dependencies: ")
        println("Sonner: ${dependencyExtension.sonner.get()}")
        println("Ktor: ${dependencyExtension.ktor.get()}")
        println("Kotlin: ${dependencyExtension.kotlin.get()}")
        println("Kotlinx Coroutines: ${dependencyExtension.kotlinxCoroutines.get()}")
        println("Koin: ${dependencyExtension.koin.get()}")
        println("Voyager: ${dependencyExtension.voyager.get()}")
    }
}