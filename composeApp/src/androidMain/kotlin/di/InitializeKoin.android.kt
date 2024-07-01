package di

import android.content.Context
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin


actual class InitializeKoin(private val context: Context) {
    actual fun initKoin() {
        startKoin {
            androidContext(context)
            androidLogger()
            modules(commonModules, commonViewModels)
        }
    }
}