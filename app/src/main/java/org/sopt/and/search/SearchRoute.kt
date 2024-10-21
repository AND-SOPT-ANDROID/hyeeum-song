package org.sopt.and.search

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import org.sopt.and.ui.theme.ANDANDROIDTheme

@Composable
fun SearchRoute(
    modifier: Modifier = Modifier,
) {
    SearchScreen()
}

@Composable
fun SearchScreen(
    modifier: Modifier = Modifier,
) {
}

@Composable
@Preview
fun SearchScreenpreview() {
    ANDANDROIDTheme {
        SearchScreen()
    }
}
