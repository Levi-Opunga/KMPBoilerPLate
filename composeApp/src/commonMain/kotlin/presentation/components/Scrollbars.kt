package presentation.components

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.gestures.ScrollableState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
expect fun HorizontalScrollbar(scrollState: ScrollableState, modifier: Modifier = Modifier)


@Composable
expect fun VerticalScrollbar(scrollState: ScrollableState, modifier: Modifier = Modifier)
