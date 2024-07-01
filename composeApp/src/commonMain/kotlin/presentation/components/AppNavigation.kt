package presentation.components

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.currentOrThrow
import cafe.adriel.voyager.transitions.SlideTransition
import di.koinViewModel
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.imageResource
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.koinInject
import presentation.screens.HomeScreen
import presentation.screens.ProfileScreen
import presentation.viewmodels.NavigationViewModel
import tunnelclient.composeapp.generated.resources.*
import ui.Colors
import ui.Fonts
import utilities.replaceWith


@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Composable
fun AppNavigation() {
    val windowSizeClass = calculateWindowSizeClass()
    val navigationScreenModel = koinViewModel<NavigationViewModel>()
    val onBackPress by navigationScreenModel.onBackPressed.collectAsState()
    var nav = LocalNavigator.currentOrThrow

    Navigator(screen = HomeScreen(),
        onBackPressed = { currentScreen ->

            onBackPress()
        }
    ) { navigator ->
        {
            nav = navigator
        }
        when (windowSizeClass.widthSizeClass) {
            WindowWidthSizeClass.Compact -> {
                MobileNavigationBar { padding ->
                    SlideTransition(navigator, modifier = Modifier.padding(padding))
                }
            }

            WindowWidthSizeClass.Medium -> {
                TabletNavigationBar { padding ->
                    SlideTransition(navigator, modifier = Modifier.padding(padding))
                }
            }

            WindowWidthSizeClass.Expanded -> {
                DesktopNavigationBar { padding ->
                    SlideTransition(navigator, modifier = Modifier.padding(padding))
                }
            }
        }
    }

}

@Composable
fun DesktopNavigationBar(content: @Composable (paddingValues: PaddingValues) -> Unit) {

    val navigator = LocalNavigator.currentOrThrow
    val scaffoldState = rememberScaffoldState()
    val viewModel = koinInject<NavigationViewModel>()
    val activeScreen by viewModel.currentTab.collectAsState()
    val headerVisible by viewModel.headerVisible.collectAsState()
    Scaffold(
        topBar = {
        },
        floatingActionButton = {
            FloatingActionsForScreens()
        }
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            NavigationRail(
                modifier = Modifier.fillMaxHeight(),
                backgroundColor = Colors.white,
                contentColor = Colors.black,
            ) {
                bottomNavigationItems.forEach { item ->
                    NavigationRailItem(
                        selected = item.screen == activeScreen,
                        onClick = {
                            if (item.screen != activeScreen) {
                                navigator.replaceWith(item.location)
                                viewModel.setCurrentTab(item.screen)
                            }
                        },
                        icon = {
                            Icon(
                                painterResource(if (item.screen == activeScreen) item.selectedIcon else item.unselectedIcon),
                                contentDescription = item.screen.label,
                                modifier = Modifier.size(32.dp),
                                tint = if (item.screen == activeScreen) Colors.black else Colors.borderGray,
                            )
                        },
                        label = {
                            Text(
                                item.screen.label.uppercase(),
                                fontFamily = Fonts.poppins(),
                                fontWeight = FontWeight.Medium,
                                fontSize = 10.sp,
                                color = if (item.screen == activeScreen) Colors.black else Colors.borderGray
                            )
                        },
                        modifier = Modifier.padding(4.dp)
                    )
                }


            }
            Column(
                modifier = Modifier.padding(it).fillMaxHeight()
            ) {
                AnimatedVisibility(
                    visible = headerVisible,
                    enter = fadeIn() + expandVertically(
                        expandFrom = Alignment.Top,
                        animationSpec = tween(1000)
                    ),
                    exit = fadeOut(
                        targetAlpha = 0f,
                        animationSpec = tween(1000)
                    ) + shrinkVertically()
                ) {
                    TopBar()
                }
                content(it)

            }
        }
    }
}


@Composable
fun TabletNavigationBar(content: @Composable (paddingValues: PaddingValues) -> Unit) {

    val navigator = LocalNavigator.currentOrThrow
    val scaffoldState = rememberScaffoldState()
    val viewModel = koinInject<NavigationViewModel>()
    val activeScreen by viewModel.currentTab.collectAsState()
    val headerVisible by viewModel.headerVisible.collectAsState()
    Scaffold(
        topBar = {
        },
        floatingActionButton = {
            FloatingActionsForScreens()
        }
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            NavigationRail(
                modifier = Modifier.fillMaxHeight(),
                backgroundColor = Colors.white,
                contentColor = Colors.black,
            ) {
                bottomNavigationItems.forEach { item ->
                    NavigationRailItem(
                        selected = item.screen == activeScreen,
                        onClick = {
                            if (item.screen != activeScreen) {
                                navigator.replaceWith(item.location)
                                viewModel.setCurrentTab(item.screen)
                            }
                        },
                        icon = {
                            Icon(
                                painterResource(if (item.screen == activeScreen) item.selectedIcon else item.unselectedIcon),
                                contentDescription = item.screen.label,
                                modifier = Modifier.size(32.dp),
                                tint = if (item.screen == activeScreen) Colors.black else Colors.borderGray,
                            )
                        },
                        label = {
                            Text(
                                item.screen.label.uppercase(),
                                fontFamily = Fonts.poppins(),
                                fontWeight = FontWeight.Medium,
                                fontSize = 10.sp,
                                color = if (item.screen == activeScreen) Colors.black else Colors.borderGray
                            )
                        },
                        modifier = Modifier.padding(4.dp)
                    )
                }


            }
            Column(
                modifier = Modifier.padding(it).fillMaxHeight()
            ) {
                AnimatedVisibility(
                    visible = headerVisible,
                    enter = fadeIn() + expandVertically(
                        expandFrom = Alignment.Top,
                        animationSpec = tween(1000)
                    ),
                    exit = fadeOut(
                        targetAlpha = 0f,
                        animationSpec = tween(1000)
                    ) + shrinkVertically()
                ) {
                    TopBar()
                }
                content(it)

            }
        }
    }
}

enum class WindowSizeClass {
    Compact,
    Regular,
    Large
}

@Composable
fun MobileNavigationBar(content: @Composable (paddingValues: PaddingValues) -> Unit) {
    val scaffoldState = rememberScaffoldState()
    val navigator = LocalNavigator.currentOrThrow
    val viewModel = koinInject<NavigationViewModel>()
    val activeScreen by viewModel.currentTab.collectAsState()
    val headerVisible by viewModel.headerVisible.collectAsState()

    Scaffold(
        topBar = {
            AnimatedVisibility(
                visible = headerVisible,
                enter = fadeIn() + expandVertically(
                    expandFrom = Alignment.Top,
                    animationSpec = tween(1000)
                ),
                exit = fadeOut(
                    targetAlpha = 0f,
                    animationSpec = tween(1000)
                ) + shrinkVertically()
            ) {
                TopBar()
            }
        },
        bottomBar = {
            BottomNavigation(
                backgroundColor = Colors.white,
                contentColor = Colors.black,
            ) {
                bottomNavigationItems.forEach { item ->

                    BottomNavigationItem(
                        selected = item.screen == activeScreen,
                        label = {
                            Text(
                                item.screen.label.uppercase(),
                                fontFamily = Fonts.poppins(),
                                fontWeight = FontWeight.Medium,
                                fontSize = 11.sp
                            )
                        },
                        icon = {
                            Icon(
                                imageResource(if (item.screen == activeScreen) item.selectedIcon else item.unselectedIcon),
                                contentDescription = item.screen.label,
                                modifier = Modifier.size(32.dp)
                            )
                        },
                        onClick = {
                            if (item.screen != activeScreen) {
//                                if (item.screen == AppNavigationScreens.Search) {
//                                    navigator.push(item.location)
//                                } else
                                navigator.replaceWith(item.location)
                                viewModel.setCurrentTab(item.screen)
                            }
                        }
                    )
                }
            }
        },
        floatingActionButton = {
            FloatingActionsForScreens()
        }

    ) {
        content(it)
    }

}


@Composable
fun FloatingActionsForScreens() {
    val navigator = LocalNavigator.currentOrThrow
    val viewModel = koinViewModel<NavigationViewModel>()
    val activeScreen by viewModel.currentTab.collectAsState()
    val headerVisible by viewModel.headerVisible.collectAsState()
    val floatingButton by viewModel.floatingActionButton.collectAsState()
    if (activeScreen == NavigationScreens.Profile && headerVisible) {
        floatingButton?.let { it() }
    }

}


data class BottomNavigationItem(
    val selected: Boolean,
    val screen: NavigationScreens,
    val unselectedIcon: DrawableResource,
    val selectedIcon: DrawableResource,
    val location: Screen
)

enum class NavigationScreens(val label: String) {
    Home(label = "Home"),
    Profile(label = "Profile"),
}

val bottomNavigationItems = listOf(
    BottomNavigationItem(
        selected = true,
        screen = NavigationScreens.Home,
        unselectedIcon = Res.drawable.ic_home_unselected,
        selectedIcon = Res.drawable.ic_home_selected,
        location = HomeScreen()
    ),
    BottomNavigationItem(
        selected = false,
        screen = NavigationScreens.Profile,
        unselectedIcon = Res.drawable.profile_dark,
        selectedIcon = Res.drawable.profile_dark,
        location = ProfileScreen()
    )

)