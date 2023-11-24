package com.nyx.textquests.extensions

import android.os.Build
import android.os.Parcelable
import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

inline fun <reified T : Parcelable?> NavGraphBuilder.composableWithCustomArgs(
    route: String,
    key: String,
    crossinline screen: @Composable (T) -> Unit,
) {
    composable(
        route = route
    ) {
        if (Build.VERSION.SDK_INT >= 33) {
            it.arguments?.getParcelable(key, T::class.java)?.let { T ->
                screen(T)
            }
        } else {
            it.arguments?.getParcelable<T>(key)?.let { T ->
                screen(T)
            }
        }
    }
}