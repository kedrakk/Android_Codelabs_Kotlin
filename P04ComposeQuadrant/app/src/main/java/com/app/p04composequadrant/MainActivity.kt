package com.app.p04composequadrant

import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.app.p04composequadrant.ui.theme.P04ComposeQuadrantTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            P04ComposeQuadrantTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    ComposeQuadrant(
                        title1 = stringResource(R.string.title1),
                        subtitle1 = stringResource(R.string.subtitle1),
                        title2 = stringResource(R.string.title2),
                        subtitle2 = stringResource(R.string.subtitle2),
                        title3 = stringResource(R.string.title3),
                        subtitle3 = stringResource(R.string.subtitle3),
                        title4 = stringResource(R.string.title4),
                        subtitle4 = stringResource(R.string.subtitle4)
                    )
                }
            }
        }
    }
}

@Composable
fun ComposeQuadrant(title1: String,subtitle1: String,title2:String,subtitle2:String,title3:String,subtitle3:String,title4:String,subtitle4:String) {
    Column() {
        Row {
            ComposeSection(title = title1, subtitle = subtitle1, backgroundColor = androidx.compose.ui.graphics.Color.Green)
            ComposeSection(title = title2, subtitle = subtitle2, backgroundColor = androidx.compose.ui.graphics.Color.Yellow)
        }
        Row {
            ComposeSection(title = title3, subtitle = subtitle3, backgroundColor = androidx.compose.ui.graphics.Color.Cyan)
            ComposeSection(title = title4, subtitle = subtitle4, backgroundColor = androidx.compose.ui.graphics.Color.LightGray)
        }
    }
}

@Composable
fun ComposeSection(title:String,subtitle:String,backgroundColor:androidx.compose.ui.graphics.Color){
    val configuration = LocalConfiguration.current
    val screenHeight= configuration.screenHeightDp.dp
    val screenWidth= configuration.screenWidthDp.dp
    Box(
        modifier = Modifier
            .background(color = backgroundColor)
            .height(screenHeight * 1 / 2)
            .width(screenWidth * 1 / 2)
            .wrapContentHeight(align = Alignment.CenterVertically)
            .wrapContentWidth(align = Alignment.CenterHorizontally)
    ){
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = title,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 16.dp),
                textAlign = TextAlign.Justify
            )
            Text(text = subtitle,textAlign = TextAlign.Justify)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    P04ComposeQuadrantTheme {
        ComposeQuadrant(
            title1 = stringResource(R.string.title1),
            subtitle1 = stringResource(R.string.subtitle1),
            title2 = stringResource(R.string.title2),
            subtitle2 = stringResource(R.string.subtitle2),
            title3 = stringResource(R.string.title3),
            subtitle3 = stringResource(R.string.subtitle3),
            title4 = stringResource(R.string.title4),
            subtitle4 = stringResource(R.string.subtitle4)
        )
    }
}