package com.nyx.textquests

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.nyx.textquests.repository.models.GraphNode
import com.nyx.textquests.ui.theme.TextQuestsTheme
import java.lang.reflect.Type


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TextQuestsTheme {
                AppNavHost(onAppClose = { finishAffinity() })
            }
        }
    }
}


