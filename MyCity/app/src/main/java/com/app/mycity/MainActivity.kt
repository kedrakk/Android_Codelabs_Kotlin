package com.app.mycity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.app.mycity.ui.theme.MyCityTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyCityTheme{
                val windowSize = calculateWindowSizeClass(activity = this)
                MyCityApp(windowSizeClass = windowSize.widthSizeClass)
            }
        }
    }
}

@Composable
fun MyCityApp(windowSizeClass: WindowWidthSizeClass) {
    MyCityScreen(windowWidthSizeClass = windowSizeClass)
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyCityTheme {
        MyCityApp(windowSizeClass = WindowWidthSizeClass.Compact)
    }
}