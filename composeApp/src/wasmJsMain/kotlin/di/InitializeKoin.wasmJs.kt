package di

import org.koin.core.context.startKoin

actual class InitializeKoin {
    actual fun initKoin() {
        startKoin {
            modules(commonModules, commonViewModels)
        }
    }
}