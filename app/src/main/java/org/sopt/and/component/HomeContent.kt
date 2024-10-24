package org.sopt.and.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.persistentListOf
import org.sopt.and.R
import org.sopt.and.ui.theme.ANDANDROIDTheme
import org.sopt.and.ui.theme.White

@Composable
fun HomeContent(
    title : String,
    items: PersistentList<Int>,
    modifier: Modifier = Modifier,
    isRanked : Boolean = false
) {
    Column {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(vertical = 20.dp, horizontal = 10.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = title,
                style = TextStyle(
                    fontSize = 20.sp,
                    color = White,
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier.weight(1f),
                overflow = TextOverflow.Ellipsis
            )
            Icon(
                imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                contentDescription = null,
                tint = White,
                modifier = Modifier.size(30.dp)
            )
        }

        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp)
                .padding(bottom = 30.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            itemsIndexed(
                items = items
            ) { index, item ->
                Box(
                    modifier = Modifier.wrapContentSize()
                ) {
                    Image(
                        painter = painterResource(item),
                        contentDescription = title,
                        modifier = Modifier.clip(shape = RoundedCornerShape(10.dp)),
                    )
                    if(isRanked){
                        Text(
                            modifier = Modifier
                                .align(Alignment.BottomStart)
                                .offset(y = 30.dp),
                            text = "${index + 1}",
                            style = TextStyle(
                                fontSize = 60.sp,
                                color = White,
                                fontWeight = FontWeight.Bold
                            )
                        )
                    }
                }
            }
        }
    }
}

@Composable
@Preview
fun VideoContentPreview() {
    ANDANDROIDTheme {
        Column {
            HomeContent(
                title = "오늘의 TOP 20",
                items = persistentListOf(
                    R.drawable.wavve_top_banner,
                    R.drawable.wavve_banner1,
                    R.drawable.wavve_banner2,
                    R.drawable.wavve_banner3
                )
            )
            HomeContent(
                title = "오늘의 TOP 20",
                isRanked = true,
                items = persistentListOf(
                    R.drawable.wavve_top_banner,
                    R.drawable.wavve_banner1,
                    R.drawable.wavve_banner2,
                    R.drawable.wavve_banner3
                )
            )
        }
    }
}
