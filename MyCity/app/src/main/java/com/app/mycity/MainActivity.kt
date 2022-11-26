package com.app.mycity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.app.mycity.ui.theme.MyCityTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyCityTheme{
                MyCityApp()
            }
        }
    }
}

@Composable
fun MyCityApp() {
    MyCityScreen()
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyCityTheme {
        MyCityApp()
    }
}