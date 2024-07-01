package utilities

import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.Navigator

fun Navigator.goTo(screen: Screen) {
    if (this.lastItem.key != screen.key) {
        push(screen)
    }
}

fun Navigator.replaceWith(screen: Screen) {
    if (this.lastItem.key != screen.key) {
        pop()
        push(screen)
    }
}