package di

import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import presentation.viewmodels.NavigationViewModel

val commonViewModels = module {
    singleOf(::NavigationViewModel)
}