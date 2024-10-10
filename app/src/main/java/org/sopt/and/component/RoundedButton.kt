package org.sopt.and.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.sopt.and.ui.theme.ANDANDROIDTheme
import org.sopt.and.ui.theme.Blue
import org.sopt.and.ui.theme.Gray
import org.sopt.and.ui.theme.White

@Composable
fun RoundedButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = false
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .fillMaxWidth()
            .clickable { if (enabled) onClick() }
            .background(
                color = if (enabled) Blue else Gray,
                shape = RoundedCornerShape(50.dp)
            )
    ) {
        Text(
            modifier = Modifier.padding(vertical = 15.dp),
            text = text,
            style = TextStyle(fontSize = 20.sp),
            color = White
        )
    }
}

@Preview
@Composable
fun RoundedButtonPreview() {
    ANDANDROIDTheme {
        RoundedButton(
            text = "",
            onClick = {}
        )
    }
}
