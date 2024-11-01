package org.sopt.and.search

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import org.sopt.and.ui.theme.ANDANDROIDTheme

@Composable
fun SearchRoute(
    paddingValues: PaddingValues,
    modifier: Modifier = Modifier,
) {
    SearchScreen(
        paddingValues = paddingValues
    )
}

@Composable
fun SearchScreen(
    paddingValues: PaddingValues,
    modifier: Modifier = Modifier,
) {
}

@Composable
@Preview
fun SearchScreenpreview() {
    ANDANDROIDTheme {
        SearchScreen(
            paddingValues = PaddingValues()
        )
    }
}
