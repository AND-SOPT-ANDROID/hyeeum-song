package org.sopt.and.signin

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import org.sopt.and.my.MyActivity
import org.sopt.and.sharedpreference.User
import org.sopt.and.sharedpreference.User.Companion.EMAIL
import org.sopt.and.sharedpreference.User.Companion.PASSWORD
import org.sopt.and.signup.SignUpActivity
import org.sopt.and.ui.theme.ANDANDROIDTheme

class SignInActivity : ComponentActivity() {
    var signUpEmail: String = ""
    var signUpPassword: String = ""

    private lateinit var user: User
    private lateinit var signUpLauncher: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        user = User(applicationContext)

        signUpLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            if (result.resultCode == RESULT_OK) {
                signUpEmail = result.data?.getStringExtra(EMAIL) ?: return@registerForActivityResult
                signUpPassword = result.data?.getStringExtra(PASSWORD) ?: return@registerForActivityResult

                user.saveUserInformation(signUpEmail, signUpPassword)
            }
        }

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ANDANDROIDTheme {
                SignInRoute(
                    navigateUp = { finish() },
                    navigateToSignUp = { navigateToSignUp() },
                    navigateToMy = { navigateToMy() },
                    signUpEmail = user.getEmail().toString(),
                    signUpPassword = user.getPassword().toString(),
                    setSignInStateTrue = { setSignInStateTrue() }
                )
            }
        }
    }

    fun setSignInStateTrue() {
        user.setSignInState(true)
    }

    fun navigateToSignUp() {
        signUpLauncher.launch(Intent(this, SignUpActivity::class.java))
    }

    fun navigateToMy() {
        Intent(this, MyActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(this)
            finish()
        }
    }
}
