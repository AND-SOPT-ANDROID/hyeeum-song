package org.sopt.and

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge
import dagger.hilt.android.AndroidEntryPoint
import org.sopt.and.my.MyActivity
import org.sopt.and.sharedpreference.User
import org.sopt.and.signin.SignInActivity

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private lateinit var user: User

    override fun onCreate(savedInstanceState: Bundle?) {
        user = User(applicationContext)

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        if (user.getSignInState()) {
            Intent(this, MyActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                startActivity(this)
                finish()
            }
        } else {
            Intent(this, SignInActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                startActivity(this)
                finish()
            }
        }
    }
}
