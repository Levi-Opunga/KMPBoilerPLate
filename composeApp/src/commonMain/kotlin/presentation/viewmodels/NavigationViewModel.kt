package presentation.viewmodels

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.dokar.sonner.ToasterState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import presentation.components.NavigationScreens
import presentation.screens.HomeScreen

class NavigationViewModel : ScreenModel{
    private  val _currentTab = MutableStateFlow(NavigationScreens.Home)
    val currentTab = _currentTab.asStateFlow()

    private val _headerVisible = MutableStateFlow(true)
    val headerVisible = this._headerVisible.asStateFlow()

    private val _homeScreenScrollState: MutableStateFlow<LazyListState?> = MutableStateFlow(null)
    val homeScreenScrollState = _homeScreenScrollState.asStateFlow()

    private val _analysisScreenScrollState: MutableStateFlow<LazyListState?> = MutableStateFlow(null)
    val analysisScreenScrollState = _analysisScreenScrollState.asStateFlow()

    private val _searchScreenScrollState: MutableStateFlow<LazyListState?> = MutableStateFlow(null)
    val searchScreenScrollState = _searchScreenScrollState.asStateFlow()

    private val _settingsScreenScrollState: MutableStateFlow<LazyListState?> = MutableStateFlow(null)
    val settingsScreenScrollState = _settingsScreenScrollState.asStateFlow()

    private val _topLeftIcon = MutableStateFlow<(@Composable () -> Unit)?>(null)
    val topLeftIcon = _topLeftIcon.asStateFlow()

    private val _floatingActionButton = MutableStateFlow<(@Composable () -> Unit)?>(null)
    val floatingActionButton = _floatingActionButton.asStateFlow()

    private val _onBackPressed = MutableStateFlow {
        true
    }
    val onBackPressed = _onBackPressed.asStateFlow()


    private val _toasterState = MutableStateFlow(
        ToasterState(
        onDismissed = {},
        coroutineScope = screenModelScope
    )
    )
    val toasterState = _toasterState.asStateFlow()


    fun setCurrentTab(tab: NavigationScreens) {
        _currentTab.value = tab
    }

    fun getCurrentTab(): NavigationScreens {
        return currentTab.value
    }
    fun toggleHeaderVisibility() {
        _headerVisible.value = !_headerVisible.value
    }
    fun makeHeaderVisible() {
        _headerVisible.value = true
    }
    fun makeHeaderInvisible() {
        _headerVisible.value = false
    }
    fun updateHomeScreenScrollState(scrollState: LazyListState) {
        _homeScreenScrollState.value = scrollState
    }
    fun updateAnalysisScreenScrollState(scrollState: LazyListState) {
        _analysisScreenScrollState.value = scrollState
    }
    fun updateSearchScreenScrollState(scrollState: LazyListState) {
        _searchScreenScrollState.value = scrollState
    }
    fun updateSettingsScreenScrollState(scrollState: LazyListState) {
        _settingsScreenScrollState.value = scrollState
    }
    fun updateToasterState(toasterState: ToasterState) {
        _toasterState.value = toasterState
    }
    fun updateOnBackPressed(onBackPressed: () -> Boolean) {
        _onBackPressed.value = onBackPressed
    }
    fun updateTopLeftIcon(icon: (@Composable () -> Unit)?) {
        _topLeftIcon.value = icon
    }
    fun updateFloatingActionButton(floatingActionButton: (@Composable () -> Unit)?) {
        _floatingActionButton.value = floatingActionButton
    }

    @Composable
    fun navigateTo(screen: NavigationScreens) {
        val navigator = LocalNavigator.currentOrThrow
        setCurrentTab(screen)
        when (screen) {
            NavigationScreens.Home -> {
                navigator.push(HomeScreen())
            }

            NavigationScreens.Profile -> {
                navigator.push(HomeScreen())
            }

        }
    }

}
