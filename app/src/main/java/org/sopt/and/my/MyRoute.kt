package org.sopt.and.my

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.sopt.and.R
import org.sopt.and.component.EventContent
import org.sopt.and.component.HistoryContent
import org.sopt.and.ui.theme.ANDANDROIDTheme
import org.sopt.and.ui.theme.Black
import org.sopt.and.ui.theme.Blue
import org.sopt.and.ui.theme.Gray
import org.sopt.and.ui.theme.LightGray
import org.sopt.and.ui.theme.White

@Composable
fun MyRoute(
    paddingValues: PaddingValues,
    email: String,
    modifier: Modifier = Modifier,
) {
    MyScreen(
        paddingValues = paddingValues,
        email = email,
        modifier = modifier
    )
}

@Composable
fun MyScreen(
    paddingValues: PaddingValues,
    email: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Black)
            .padding(paddingValues),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .background(Gray)
                .padding(horizontal = 10.dp),
            horizontalArrangement = Arrangement.spacedBy(15.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Filled.AccountCircle,
                contentDescription = null,
                tint = Blue,
                modifier = Modifier.size(60.dp)
            )

            Text(
                text = stringResource(R.string.username, email),
                style = TextStyle(
                    fontSize = 20.sp,
                    color = White
                ),
                modifier = Modifier
                    .padding(vertical = 40.dp)
                    .weight(1f),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            Icon(
                imageVector = Icons.Outlined.Notifications,
                contentDescription = null,
                tint = LightGray,
                modifier = Modifier
                    .size(30.dp)
                    .clickable { }
            )

            Icon(
                imageVector = Icons.Outlined.Settings,
                contentDescription = null,
                tint = LightGray,
                modifier = Modifier
                    .size(30.dp)
                    .clickable { }
            )
        }

        EventContent(
            description = stringResource(R.string.event_first_payment),
            title = stringResource(R.string.buy)
        )

        HorizontalDivider(
            thickness = 2.dp,
            color = Black
        )

        EventContent(
            description = stringResource(R.string.present_ticket),
            title = stringResource(R.string.buy)
        )

        Spacer(modifier = Modifier.height(20.dp))

        HistoryContent(
            title = stringResource(R.string.viewing_history),
            description = stringResource(R.string.no_viewing_history)
        )

        HistoryContent(
            title = stringResource(R.string.interested_program),
            description = stringResource(R.string.no_interested_program)
        )
    }
}

@Composable
@Preview
fun MyScreenPreview() {
    ANDANDROIDTheme {
        MyScreen(
            paddingValues = PaddingValues(),
            email = ""
        )
    }
}
