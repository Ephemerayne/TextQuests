package com.nyx.textquests

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.nyx.textquests.navigation.MainScreenNavigationImpl
import com.nyx.textquests.navigation.NavigationTree
import com.nyx.textquests.navigation.TextQuestScreenNavigationImpl
import com.nyx.textquests.repository.GraphLocalRepository
import com.nyx.textquests.screens.main.ui.MainScreen
import com.nyx.textquests.screens.textquest.ui.TextQuestScreen

private const val ARGS = "args"

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = NavigationTree.Main.MainMenu.screenName,
    onAppClose: () -> Unit,
) {
    val dataRepo = GraphLocalRepository(LocalContext.current.assets)

    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination,
    ) {
        val mainMenuScreenNavigation = MainScreenNavigationImpl(navController, onAppClose)
        val textQuestScreenNavigation = TextQuestScreenNavigationImpl(navController)

        composable(startDestination) {
            MainScreen(
                screenNavigation = mainMenuScreenNavigation
            )
        }

        composable(
            route = NavigationTree.Main.TextQuest.screenName

        ) {
            TextQuestScreen(
                repository = dataRepo,
                screenNavigation = textQuestScreenNavigation,
            )
        }
    }
}

@Composable
fun EnterAnimation(content: @Composable () -> Unit) {
    AnimatedVisibility(
        visibleState = MutableTransitionState(
            initialState = false
        ).apply { targetState = true },
        modifier = Modifier,
        enter = slideInVertically(
            initialOffsetY = { -40 }
        ) + expandVertically(
            expandFrom = Alignment.Top
        ) + fadeIn(initialAlpha = 0.3f),
        exit = slideOutVertically() + shrinkVertically() + fadeOut(),
    ) {
        content()
    }
}
