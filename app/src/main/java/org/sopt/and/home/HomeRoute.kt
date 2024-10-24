package org.sopt.and.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.util.lerp
import kotlinx.collections.immutable.persistentListOf
import org.sopt.and.R
import org.sopt.and.component.HomeContent
import org.sopt.and.component.TopBar
import org.sopt.and.home.HomeViewModel.Companion.MAX_PAGE
import org.sopt.and.ui.theme.ANDANDROIDTheme
import org.sopt.and.ui.theme.Black
import org.sopt.and.ui.theme.White
import kotlin.math.absoluteValue

@Composable
fun HomeRoute(
    paddingValues: PaddingValues,
    modifier: Modifier = Modifier,
) {
    HomeScreen(
        paddingValues = paddingValues
    )
}

@Composable
fun HomeScreen(
    paddingValues: PaddingValues,
    modifier: Modifier = Modifier,
) {
    val pagerState = rememberPagerState(pageCount = { MAX_PAGE })

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(Black)
            .padding(paddingValues),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(30.dp)
    ) {
        item {
            TopBar(
                leadingIcon = {
                    Text(
                        text = stringResource(R.string.wavve),
                        style = TextStyle(
                            color = White,
                            fontWeight = FontWeight.Bold,
                            fontSize = 30.sp
                        ),
                    )
                }
            )
        }

        item {
            HorizontalPager(
                state = pagerState,
                modifier = Modifier
                    .fillMaxWidth()
            ) { page ->
                Box(
                    modifier = Modifier
                        .padding(horizontal = 20.dp)
                ) {
                    Image(
                        modifier = Modifier
                            .fillMaxSize()
                            .clip(shape = RoundedCornerShape(10.dp))
                            .graphicsLayer {
                                val pageOffset = (
                                        (pagerState.currentPage - page) + pagerState
                                            .currentPageOffsetFraction
                                        ).absoluteValue

                                alpha = lerp(
                                    start = 0.5f,
                                    stop = 1f,
                                    fraction = 1f - pageOffset.coerceIn(0f, 1f)
                                )
                            },
                        painter = painterResource(R.drawable.wavve_top_banner),
                        contentScale = ContentScale.Crop,
                        contentDescription = "top banner"
                    )
                    Text(
                        text = "${page + 1}/${MAX_PAGE}",
                        style = TextStyle(
                            fontSize = 14.sp,
                            color = White,
                            fontWeight = FontWeight.Bold
                        ),
                        modifier = Modifier
                            .align(Alignment.BottomEnd)
                            .padding(bottom = 6.dp)
                            .padding(end = 10.dp)
                    )
                }
            }
        }

        item {
            HomeContent(
                title = stringResource(R.string.editor_recommend_contents),
                items = persistentListOf(
                    R.drawable.wavve_banner1,
                    R.drawable.wavve_banner2,
                    R.drawable.wavve_banner3,
                    R.drawable.wavve_top_banner,
                )
            )
        }

        item {
            HomeContent(
                title = stringResource(R.string.top_20_contents),
                isRanked = true,
                items = persistentListOf(
                    R.drawable.wavve_banner2,
                    R.drawable.wavve_top_banner,
                    R.drawable.wavve_banner1,
                    R.drawable.wavve_banner3
                )
            )
        }
    }
}

@Composable
@Preview
fun HomeScreenpreview() {
    ANDANDROIDTheme {
        HomeScreen(
            paddingValues = PaddingValues()
        )
    }
}
