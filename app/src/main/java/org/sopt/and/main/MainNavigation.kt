package org.sopt.and.main

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import org.sopt.and.home.Home
import org.sopt.and.home.navigateHome
import org.sopt.and.my.navigateMy
import org.sopt.and.search.navigateSearch
import org.sopt.and.sharedpreference.User
import org.sopt.and.signin.SignIn
import org.sopt.and.signin.navigateSignIn
import org.sopt.and.signup.navigateSignUp

class MainNavigation(
    val navController: NavHostController,
    val startDestination: MainTabRoute
) {
    private val currentDestination: NavDestination?
        @Composable get() = navController
            .currentBackStackEntryAsState().value?.destination

    val currentTab: MainBottomTab?
        @Composable
        get() = MainBottomTab.find { tab ->
            currentDestination?.hasRoute(tab::class) == true
        }

    val navOptions = navOptions {
        popUpTo<Home> {
            inclusive = false
            saveState = true
        }
        launchSingleTop = true
        restoreState = true
    }

    fun navigate(tab: MainBottomTab) {
        when (tab) {
            MainBottomTab.Home -> navController.navigateHome(navOptions = navOptions)

            MainBottomTab.Search -> navController.navigateSearch(navOptions = navOptions)

            MainBottomTab.My -> navController.navigateMy(navOptions = navOptions)
        }
    }

    fun navigateUp() {
        navController.navigateUp()
    }

    fun navigateToSignIn() {
        navController.navigateSignIn()
    }

    fun navigateToSignUp() {
        navController.navigateSignUp()
    }

    fun navigateToHome() {
        navController.navigateHome(navOptions = navOptions)
    }

    @Composable
    fun shouldShowBottomBar() = MainBottomTab.contains {
        currentDestination?.hasRoute(it::class) == true
    }
}

@Composable
fun rememberMainNavigator(
    navController: NavHostController = rememberNavController(),
    user: User
): MainNavigation = remember(navController) {
    val startDestination = if (user.getSignInState()) Home else SignIn
    MainNavigation(navController, startDestination)
}
