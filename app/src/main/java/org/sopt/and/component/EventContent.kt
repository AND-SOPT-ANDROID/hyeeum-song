package org.sopt.and.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.sopt.and.ui.theme.Gray
import org.sopt.and.ui.theme.LightGray
import org.sopt.and.ui.theme.White

@Composable
fun EventContent(
    description: String,
    title: String,
    modifier: Modifier = Modifier
) { //네이밍 애매..
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(Gray)
            .padding(horizontal = 5.dp)
            .padding(top = 5.dp, bottom = 15.dp),
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = description,
            style = TextStyle(
                fontSize = 18.sp,
                color = LightGray
            )
        )

        Row(
            modifier = Modifier.padding(vertical = 5.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = title,
                style = TextStyle(
                    fontSize = 18.sp,
                    color = White
                )
            )
            Icon(
                imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                contentDescription = null,
                tint = White,
                modifier = Modifier.size(20.dp)
            )
        }
    }
}
