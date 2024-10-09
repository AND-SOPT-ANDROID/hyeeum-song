package org.sopt.and.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.sopt.and.ui.theme.Gray
import org.sopt.and.ui.theme.LightGray
import org.sopt.and.ui.theme.White

@Composable
fun SignUpTextField(
    value: String,
    onValueChange: (String) -> Unit,
    textPaddingValue : Int,
    placeholder: String,
    modifier: Modifier = Modifier,
    trailingIcon: @Composable () -> Unit = {},
    isTrailingIconUsed: Boolean = false,
    isPasswordVisible: Boolean = false
) {
    BasicTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier
            .fillMaxWidth()
            .background(color = Gray, shape = RoundedCornerShape(5.dp)),
        textStyle = TextStyle(color = White, fontSize = 16.sp),
        visualTransformation = if (isTrailingIconUsed && !isPasswordVisible) PasswordVisualTransformation() else VisualTransformation.None,
        singleLine = true,
        decorationBox = { innerTextField ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = textPaddingValue.dp, horizontal = 10.dp)
            ) {
                Box(
                    modifier = Modifier.weight(1f)
                ) {
                    if (value.isEmpty()) {
                        Text(
                            text = placeholder,
                            style = TextStyle(color = LightGray, fontSize = 16.sp)
                        )
                    }
                    innerTextField()
                }
                trailingIcon()
            }
        }
    )
}
