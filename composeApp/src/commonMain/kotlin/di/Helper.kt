package di

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import org.koin.compose.currentKoinScope
import org.koin.compose.koinInject

@Composable
inline fun  <reified T :ScreenModel> koinViewModel():T{
    return koinInject<T>()
}