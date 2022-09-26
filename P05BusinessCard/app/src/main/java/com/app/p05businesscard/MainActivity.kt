package com.app.p05businesscard

import android.graphics.fonts.FontStyle
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.p05businesscard.ui.theme.P05BusinessCardTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            P05BusinessCardTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    BusinessCard(
                        name="Android",
                    )
                }
            }
        }
    }
}

@Composable
fun BusinessCard(name: String) {
    Box (
        modifier = Modifier
            .background(
                color = colorResource(id = R.color.bgColor)
            )
            .fillMaxHeight()
            .fillMaxWidth(),
        contentAlignment = Alignment.Center
    ){
        ImageWithText()
    }
}

@Composable
fun ImageWithText(){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_foreground),
            contentDescription = null,
            contentScale = ContentScale.FillWidth
        )
        Text(text = "Jennifer Doe", color = colorResource(id = R.color.white), fontSize = 40.sp, fontWeight = FontWeight.Light, modifier = Modifier.padding(bottom = 10.dp))
        Text(text = "Android Developer Extraordinaire", color = colorResource(id = R.color.iconColor), fontSize = 14.sp, fontWeight = FontWeight.Bold)
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    P05BusinessCardTheme {
        BusinessCard(
            name="Android",
        )
    }
}