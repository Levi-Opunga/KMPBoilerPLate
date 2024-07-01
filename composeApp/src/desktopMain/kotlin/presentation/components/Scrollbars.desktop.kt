package presentation.components

import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.ScrollableState
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.text.TextFieldScrollState
import androidx.compose.foundation.v2.ScrollbarAdapter
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ui.Colors

@OptIn(ExperimentalFoundationApi::class)
@Composable
actual fun HorizontalScrollbar(scrollState: ScrollableState, modifier: Modifier) {

    when (scrollState) {
        is ScrollState -> {
            ScrollBarWithDefaults(rememberScrollbarAdapter(scrollState), modifier, ScrollDirection.Horizontal)
        }

        is LazyListState -> {
            ScrollBarWithDefaults(rememberScrollbarAdapter(scrollState), modifier, ScrollDirection.Horizontal)
        }

        is LazyGridState -> {
            ScrollBarWithDefaults(rememberScrollbarAdapter(scrollState), modifier, ScrollDirection.Horizontal)
        }

        is TextFieldScrollState -> {
            ScrollBarWithDefaults(rememberScrollbarAdapter(scrollState), modifier, ScrollDirection.Horizontal)
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
actual fun VerticalScrollbar(scrollState: ScrollableState, modifier: Modifier) {
    when (scrollState) {
        is ScrollState -> {
            ScrollBarWithDefaults(rememberScrollbarAdapter(scrollState), modifier)
        }

        is LazyListState -> {

            ScrollBarWithDefaults(rememberScrollbarAdapter(scrollState), modifier)
        }

        is LazyGridState -> {
            ScrollBarWithDefaults(rememberScrollbarAdapter(scrollState), modifier)
        }

        is TextFieldScrollState -> {
            ScrollBarWithDefaults(rememberScrollbarAdapter(scrollState), modifier)
        }
    }

}

@Composable
fun ScrollBarWithDefaults(
    scrollBarAdapter: ScrollbarAdapter,
    modifier: Modifier = Modifier,
    direction: ScrollDirection = ScrollDirection.Vertical,
    style: ScrollbarStyle = defaultScrollbarStyle().copy(
        hoverColor = Colors.almostBlack,
        unhoverColor = Colors.almostBlack.copy(alpha = 0.82f),
    ),
) {

    if (direction == ScrollDirection.Horizontal) {
        HorizontalScrollbar(scrollBarAdapter, modifier.background(Colors.homeGray).fillMaxWidth(), false, style)
    } else {
        VerticalScrollbar(scrollBarAdapter, modifier.background(Colors.homeGray).width(12.dp), false, style)
    }
}

enum class ScrollDirection {
    Vertical,
    Horizontal
}
