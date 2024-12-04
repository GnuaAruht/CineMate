package com.gnua_aruht.cinemate

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.view.WindowInsetsControllerCompat
import androidx.datastore.core.DataStore
import com.gnua_aruht.cinemate.presentation.navigation.AppNavGraph
import com.gnua_aruht.cinemate.presentation.navigation.Route
import com.gnua_aruht.cinemate.presentation.theme.CineMateTheme
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var userPref: DataStore<UserPreferences>

    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        WindowInsetsControllerCompat(window, window.decorView).isAppearanceLightStatusBars = false
        val appHaveLaunched = runBlocking { userPref.data.first().appHaveLaunched }
        val startDestination = if (appHaveLaunched) Route.Home else Route.Intro
        setContent {
            CineMateTheme {
                AppNavGraph(
                    startDestination = startDestination,
                    windowSizeClass = calculateWindowSizeClass(activity = this)
                )
            }
        }
    }

}
