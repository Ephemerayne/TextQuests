package com.nyx.textquests.navigation

import android.app.Activity
import androidx.navigation.NavController
import com.nyx.textquests.screens.main.api.MainScreenNavigation

class MainScreenNavigationImpl(
    private val navController: NavController,
    private val onAppClose: () -> Unit
) : MainScreenNavigation {

    override fun startNewGame() {
        navController.navigate(route = NavigationTree.Main.TextQuest.screenName)
    }

    override fun loadSavedGame() {
       // TODO
    }

    override fun exit() {
       onAppClose.invoke()
    }
}