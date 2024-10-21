package org.sopt.and.home

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import org.sopt.and.ui.theme.ANDANDROIDTheme

@Composable
fun HomeRoute(
    modifier: Modifier = Modifier,
) {
    HomeScreen()
}

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
) {
}

@Composable
@Preview
fun HomeScreenpreview() {
    ANDANDROIDTheme {
        HomeScreen()
    }
}
