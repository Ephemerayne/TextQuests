package com.nyx.textquests.screens.main.impl

sealed class NavigationEvent {
    object NewGame : NavigationEvent()
    object LoadGame : NavigationEvent()
    object Exit : NavigationEvent()
    object None : NavigationEvent()
}