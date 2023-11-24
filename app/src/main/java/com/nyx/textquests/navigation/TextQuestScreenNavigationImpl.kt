package com.nyx.textquests.navigation

import androidx.navigation.NavController
import com.nyx.textquests.screens.textquest.api.TextQuestScreenNavigation

class TextQuestScreenNavigationImpl(
    private val navController: NavController,
) : TextQuestScreenNavigation {

    override fun back() {
        navController.popBackStack()
    }
}