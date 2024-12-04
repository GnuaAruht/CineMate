package com.gnua_aruht.cinemate

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowInsetsControllerCompat
import androidx.datastore.core.DataStore
import com.gnua_aruht.cinemate.presentation.navigation.AppNavGraph
import com.gnua_aruht.cinemate.presentation.navigation.Route
import com.gnua_aruht.cinemate.presentation.theme.CineMateTheme
import dagger.hilt.android.AndroidEntryPoint
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
        val splashScreen = installSplashScreen()
        WindowInsetsControllerCompat(window, window.decorView).isAppearanceLightStatusBars = false
        val appHaveLaunched = runBlocking { userPref.data.first().appHaveLaunched }
        splashScreen.setKeepOnScreenCondition { false }
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
