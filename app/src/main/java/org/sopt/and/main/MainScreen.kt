package org.sopt.and.main

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import kotlinx.collections.immutable.toPersistentList
import org.sopt.and.home.homeNavGraph
import org.sopt.and.my.myNavGraph
import org.sopt.and.search.searchNavGraph
import org.sopt.and.sharedpreference.User
import org.sopt.and.signin.signInNavGraph
import org.sopt.and.signup.signUpNavGraph

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
                    setSignInStateTrue = { navigator.setSignInStateTrue() },
                    signUpEmail = user.getEmail().toString(),
                    signUpPassword = user.getPassword().toString(),
                )
                signUpNavGraph(
                    navigateUp = { navigator.navigateUp() },
                    navigateToSignIn = { email, password -> navigator.navigateToSignIn() },
                    saveUserInformation = { email, password ->
                        user.saveUserInformation(
                            email,
                            password
                        )
                    }
                )
                homeNavGraph(
                    paddingValues = innerPadding
                )
                searchNavGraph(
                    paddingValues = innerPadding
                )
                myNavGraph(
                    paddingValues = innerPadding,
                    user = user
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


