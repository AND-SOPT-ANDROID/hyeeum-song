package org.sopt.and.signup

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import org.sopt.and.sharedpreference.User.Companion.EMAIL
import org.sopt.and.sharedpreference.User.Companion.PASSWORD
import org.sopt.and.ui.theme.ANDANDROIDTheme

class SignUpActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ANDANDROIDTheme {
                SignUpRoute(
                    navigateUp = { finish() },
                    navigateToSignIn = { email, password -> navigateToSignIn(email, password) }
                )
            }
        }
    }

    fun navigateToSignIn(email: String, password: String) {
        intent.apply {
            putExtra(EMAIL, email)
            putExtra(PASSWORD, password)
        }
        setResult(RESULT_OK, intent)
        finish()
    }
}
