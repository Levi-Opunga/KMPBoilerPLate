package di

import cafe.adriel.voyager.core.model.screenModelScope
import com.dokar.sonner.ToasterState
import kotlinx.coroutines.*
import org.koin.dsl.module

@OptIn(DelicateCoroutinesApi::class)
val commonModules = module {
  single { "Hello World" }
  single{
    ToasterState(
      onDismissed = {},
      coroutineScope = GlobalScope
    )
  }
}