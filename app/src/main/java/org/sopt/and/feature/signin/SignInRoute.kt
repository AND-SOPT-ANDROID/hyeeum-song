package org.sopt.and.feature.signin

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material3.Icon
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.flowWithLifecycle
import org.sopt.and.R
import org.sopt.and.feature.component.RoundedButton
import org.sopt.and.feature.component.AuthTextField
import org.sopt.and.feature.component.TopBar
import org.sopt.and.ui.theme.ANDANDROIDTheme
import org.sopt.and.ui.theme.Black
import org.sopt.and.ui.theme.LightGray
import org.sopt.and.ui.theme.White

@Composable
fun SignInRoute(
    navigateUp: () -> Unit,
    navigateToSignUp: () -> Unit,
    navigateToHome: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: SignInViewModel = hiltViewModel(),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val lifecycleOwner = LocalLifecycleOwner.current
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(viewModel.sideEffect, lifecycleOwner) {
        viewModel.sideEffect.flowWithLifecycle(lifecycleOwner.lifecycle)
            .collect { sideEffect ->
                when (sideEffect) {
                    is SignInSideEffect.NavigateToSignUp ->
                        navigateToSignUp()

                    is SignInSideEffect.NavigateToHome -> {
                        navigateToHome()
                    }

                    is SignInSideEffect.ShowSnackBar ->
                        snackbarHostState.showSnackbar(sideEffect.snackBarMessage)
                }
            }
    }

    SignInScreen(
        navigateUp = navigateUp,
        navigateToSignUp = navigateToSignUp,
        username = state.username,
        password = state.password,
        onUsernameChange = viewModel::setUsername,
        onPasswordChange = viewModel::setPassword,
        isPasswordVisible = state.isPasswordVisible,
        reversePasswordVisibility = viewModel::reversePasswordVisibility,
        isButtonEnabled = state.isButtonEnabled,
        isSignInValid = viewModel::isSignInValid,
        snackbarHostState = snackbarHostState,
        modifier = modifier
    )
}

@Composable
fun SignInScreen(
    navigateUp: () -> Unit,
    navigateToSignUp: () -> Unit,
    username: String,
    password: String,
    onUsernameChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    isPasswordVisible: Boolean,
    reversePasswordVisibility: () -> Unit,
    isButtonEnabled: Boolean,
    isSignInValid: () -> Unit,
    snackbarHostState: SnackbarHostState,
    modifier: Modifier = Modifier
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
            leadingIcon = {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                    contentDescription = null,
                    tint = White,
                    modifier = Modifier
                        .size(40.dp)
                        .clickable { navigateUp() }
                )
            },
            content = {
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

        Spacer(modifier = Modifier.height(50.dp))

        Column(
            modifier = modifier.padding(horizontal = 10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AuthTextField(
                value = username,
                onValueChange = onUsernameChange,
                textPaddingValue = 20,
                placeholder = stringResource(R.string.signin_username_placeholder)
            )

            Spacer(modifier = Modifier.height(5.dp))

            AuthTextField(
                value = password,
                onValueChange = onPasswordChange,
                textPaddingValue = 20,
                placeholder = stringResource(R.string.signin_password_placeholder),
                trailingIcon = {
                    Text(
                        color = White,
                        text = if (isPasswordVisible) stringResource(R.string.show) else stringResource(
                            R.string.hide
                        ),
                        modifier = Modifier.clickable { reversePasswordVisibility() }
                    )
                },
                isTrailingIconUsed = true,
                isPasswordVisible = isPasswordVisible
            )

            Spacer(modifier = Modifier.height(30.dp))

            RoundedButton(
                text = stringResource(R.string.signin),
                onClick = {
                    isSignInValid()
                },
                enabled = isButtonEnabled
            )

            Spacer(modifier = Modifier.height(20.dp))

            Row(
                modifier = Modifier,
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(10.dp),
            ) {
                Text(
                    text = stringResource(R.string.find_id),
                    style = TextStyle(fontSize = 13.sp),
                    color = LightGray
                )
                Text(
                    text = "|",
                    style = TextStyle(fontSize = 13.sp),
                    color = LightGray
                )
                Text(
                    text = stringResource(R.string.reset_password),
                    style = TextStyle(fontSize = 13.sp),
                    color = LightGray
                )
                Text(
                    text = "|",
                    style = TextStyle(fontSize = 13.sp),
                    color = LightGray
                )
                Text(
                    text = stringResource(R.string.join),
                    style = TextStyle(fontSize = 13.sp),
                    color = LightGray,
                    modifier = Modifier.clickable {
                        navigateToSignUp()
                    }
                )
            }
        }
        Spacer(modifier = Modifier.weight(1f))

        SnackbarHost(
            hostState = snackbarHostState
        )
    }
}

@Composable
@Preview
fun SignInScreenPreview() {
    ANDANDROIDTheme {
        SignInScreen(
            navigateUp = {},
            navigateToSignUp = {},
            username = "",
            password = "",
            onUsernameChange = {},
            onPasswordChange = {},
            isPasswordVisible = false,
            reversePasswordVisibility = {},
            isButtonEnabled = false,
            isSignInValid = { },
            snackbarHostState = SnackbarHostState()
        )
    }
}
