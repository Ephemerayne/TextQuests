package com.nyx.textquests.screens.main.impl

import androidx.lifecycle.ViewModel
import com.nyx.textquests.screens.main.api.MainScreenNavigation

class MainViewModel : ViewModel() {

    fun onMenuItemClick(menuItem: MenuItem, navigation: MainScreenNavigation) {
        when (menuItem) {
            MenuItem.NEW -> navigation.startNewGame()
            MenuItem.LOAD -> navigation.loadSavedGame()
            MenuItem.EXIT -> navigation.exit()
            MenuItem.NONE -> Unit
        }
    }
}