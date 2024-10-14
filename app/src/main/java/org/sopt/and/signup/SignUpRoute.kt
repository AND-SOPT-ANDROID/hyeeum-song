package org.sopt.and.signup

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.flowWithLifecycle
import org.sopt.and.R
import org.sopt.and.component.ExpandedButton
import org.sopt.and.component.SignUpTextField
import org.sopt.and.component.TopBar
import org.sopt.and.ui.theme.ANDANDROIDTheme
import org.sopt.and.ui.theme.Black
import org.sopt.and.ui.theme.LightGray
import org.sopt.and.ui.theme.White

@Composable
fun SignUpRoute(
    navigateUp: () -> Unit,
    navigateToSignIn: (String, String) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: SignUpViewModel = hiltViewModel(),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val lifecycleOwner = LocalLifecycleOwner.current
    val context = LocalContext.current

    LaunchedEffect(viewModel.sideEffect, lifecycleOwner) {
        viewModel.sideEffect.flowWithLifecycle(lifecycleOwner.lifecycle)
            .collect { sideEffect ->
                when (sideEffect) {
                    is SignUpSideEffect.NavigateToSignIn ->
                        navigateToSignIn(state.email, state.password)


                    is SignUpSideEffect.ShowToast -> Toast.makeText(
                        context,
                        sideEffect.toastMessage,
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
    }

    SignUpScreen(
        navigateUp = navigateUp,
        email = state.email,
        password = state.password,
        onEmailChange = viewModel::setEmail,
        onPasswordChange = viewModel::setPassword,
        isPasswordVisible = state.isPasswordVisible,
        reversePasswordVisibility = viewModel::reversePasswordVisibility,
        isButtonEnabled = state.isButtonEnabled,
        checkSignUpValidation = viewModel::isSignUpValid,
        modifier = modifier
    )
}

@Composable
fun SignUpScreen(
    navigateUp: () -> Unit,
    email: String,
    password: String,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    isPasswordVisible: Boolean,
    reversePasswordVisibility: () -> Unit,
    isButtonEnabled: Boolean,
    checkSignUpValidation: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Black)
            .statusBarsPadding()
            .navigationBarsPadding()
            .imePadding(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TopBar(
            content = {
                Text(
                    text = stringResource(R.string.signup),
                    style = TextStyle(White),
                    fontSize = 20.sp
                )
            },
            trailingIcon = {
                Icon(
                    imageVector = Icons.Filled.Close,
                    contentDescription = null,
                    tint = White,
                    modifier = Modifier
                        .clickable { navigateUp() }
                        .size(40.dp)
                )
            }
        )

        Column(
            modifier = modifier
                .padding(horizontal = 10.dp)
                .imePadding()
        ) {
            Text(
                color = White,
                lineHeight = 40.sp,
                text = stringResource(R.string.signup_title),
                fontSize = 25.sp,
                modifier = Modifier.padding(top = 30.dp, bottom = 20.dp)
            )

            SignUpTextField(
                value = email,
                onValueChange = onEmailChange,
                textPaddingValue = 15,
                placeholder = stringResource(R.string.signup_id_example)
            )


            Text(
                color = LightGray,
                text = stringResource(R.string.signup_id_notification),
                fontSize = 12.sp,
                modifier = Modifier.padding(top = 5.dp)
            )

            Spacer(modifier = Modifier.height(20.dp))

            SignUpTextField(
                value = password,
                onValueChange = onPasswordChange,
                textPaddingValue = 15,
                placeholder = stringResource(R.string.signup_password_placeholder),
                trailingIcon = {
                    Text(
                        color = White,
                        text = if (isPasswordVisible) stringResource(R.string.hide) else stringResource(
                            R.string.show
                        ),
                        modifier = Modifier.clickable { reversePasswordVisibility() }
                    )
                },
                isTrailingIconUsed = true,
                isPasswordVisible = isPasswordVisible
            )

            Text(
                color = LightGray,
                text = stringResource(R.string.signup_password_notification),
                fontSize = 12.sp,
                modifier = Modifier.padding(top = 5.dp)
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        ExpandedButton(
            text = stringResource(R.string.wavve_sign_up),
            onClick = checkSignUpValidation,
            enabled = isButtonEnabled
        )
    }
}

@Composable
@Preview
fun SignUpScreenPreview() {
    ANDANDROIDTheme {
        SignUpScreen(
            navigateUp = {},
            email = "",
            password = "",
            onEmailChange = {},
            onPasswordChange = {},
            isPasswordVisible = false,
            reversePasswordVisibility = {},
            isButtonEnabled = false,
            checkSignUpValidation = {},
            modifier = Modifier
        )
    }
}
