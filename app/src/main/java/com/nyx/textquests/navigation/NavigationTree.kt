package com.nyx.textquests.navigation

sealed class NavigationTree {
    enum class Main(val screenName: String) {
        MainMenu("main_menu"),
        TextQuest("text_quest"),
        Options("options")
    }
}