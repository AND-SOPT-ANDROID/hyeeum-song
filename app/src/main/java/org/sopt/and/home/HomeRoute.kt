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
import androidx.compose.runtime.getValue
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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.persistentListOf
import org.sopt.and.R
import org.sopt.and.component.HomeContent
import org.sopt.and.component.TopBar
import org.sopt.and.home.model.ContentModel
import org.sopt.and.ui.theme.ANDANDROIDTheme
import org.sopt.and.ui.theme.Black
import org.sopt.and.ui.theme.White
import kotlin.math.absoluteValue

@Composable
fun HomeRoute(
    paddingValues: PaddingValues,
    modifier: Modifier = Modifier,
    homeViewModel: HomeViewModel = hiltViewModel()
) {
    val state by homeViewModel.state.collectAsStateWithLifecycle()

    HomeScreen(
        paddingValues = paddingValues,
        topBannerContent = state.topBannerContent,
        recommendContent = state.recommendContent,
        top20Content = state.top20Content
    )
}

@Composable
fun HomeScreen(
    paddingValues: PaddingValues,
    topBannerContent: PersistentList<ContentModel>,
    recommendContent: PersistentList<ContentModel>,
    top20Content: PersistentList<ContentModel>,
    modifier: Modifier = Modifier,
) {
    val pagerState = rememberPagerState(pageCount = { topBannerContent.size })

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
                val rankModel = topBannerContent[page]
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
                        painter = painterResource(rankModel.image),
                        contentScale = ContentScale.Crop,
                        contentDescription = "top banner"
                    )
                    Text(
                        text = "${rankModel.rank}/${topBannerContent.size}",
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
                items = recommendContent
            )
        }

        item {
            HomeContent(
                title = stringResource(R.string.top_20_contents),
                isRanked = true,
                items = top20Content
            )
        }
    }
}

@Composable
@Preview
fun HomeScreenpreview() {
    ANDANDROIDTheme {
        HomeScreen(
            paddingValues = PaddingValues(),
            topBannerContent = persistentListOf(),
            recommendContent = persistentListOf(),
            top20Content = persistentListOf()
        )
    }
}
