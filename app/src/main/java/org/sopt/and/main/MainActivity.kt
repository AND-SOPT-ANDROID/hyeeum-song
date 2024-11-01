package org.sopt.and.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import org.sopt.and.sharedpreference.User
import org.sopt.and.ui.theme.ANDANDROIDTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private lateinit var user: User

    override fun onCreate(savedInstanceState: Bundle?) {
        user = User(applicationContext)

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navigator = rememberNavController()
            val mainNavigator = rememberMainNavigator(navigator, user)

            ANDANDROIDTheme {
                MainScreen(user = user, navigator = mainNavigator)
            }
        }
    }
}

