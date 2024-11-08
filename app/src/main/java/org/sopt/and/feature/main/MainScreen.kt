package org.sopt.and.feature.main

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import kotlinx.collections.immutable.toPersistentList
import org.sopt.and.feature.home.homeNavGraph
import org.sopt.and.feature.my.myNavGraph
import org.sopt.and.feature.search.searchNavGraph
import org.sopt.and.feature.signin.signInNavGraph
import org.sopt.and.feature.signup.signUpNavGraph
import org.sopt.and.sharedpreference.User

@Composable
fun MainScreen(
    user: User,
    navigator: MainNavigation = rememberMainNavigator(user = user)
) {
    Scaffold(
        content = { innerPadding ->
            NavHost(
                navController = navigator.navController,
                startDestination = navigator.startDestination
            ) {
                signInNavGraph(
                    navigateUp = { navigator.navigateUp() },
                    navigateToSignUp = { navigator.navigateToSignUp() },
                    navigateToHome = { navigator.navigateToHome() },
                )
                signUpNavGraph(
                    navigateUp = { navigator.navigateUp() },
                    navigateToSignIn = { username, password -> navigator.navigateToSignIn() },
                )
                homeNavGraph(
                    paddingValues = innerPadding
                )
                searchNavGraph(
                    paddingValues = innerPadding
                )
                myNavGraph(
                    paddingValues = innerPadding,
                )
            }
        },
        bottomBar = {
            MainBottomBar(
                isVisible = navigator.shouldShowBottomBar(),
                tabs = MainBottomTab.entries.toPersistentList(),
                currentTab = navigator.currentTab,
                onTabSelected = { selectedTab -> navigator.navigate(selectedTab) }
            )
        }
    )
}


