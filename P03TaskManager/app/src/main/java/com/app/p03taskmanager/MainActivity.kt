package com.app.p03taskmanager

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.p03taskmanager.ui.theme.P03TaskManagerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            P03TaskManagerTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    TaskManagerCard(title = stringResource(R.string.title), subtitle = stringResource(R.string.subtitle))
                }
            }
        }
    }
}

@Composable
fun TaskManagerCard(title: String,subtitle:String) {
    Column (
        modifier = Modifier.wrapContentHeight(
            align = Alignment.CenterVertically
        ).wrapContentWidth(align = Alignment.CenterHorizontally)
    ){
        Image(
            painter = painterResource(id = R.drawable.ic_task_completed),
            contentDescription = null,
        )
        Text(
            text = title,
            fontSize = 24.sp,
            modifier = Modifier.padding(top = 24.dp, bottom = 8.dp),
        )
        Text(
            text = subtitle,
            fontSize = 16.sp,
            textAlign = TextAlign.Justify
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    P03TaskManagerTheme {
        TaskManagerCard(title = stringResource(R.string.title), subtitle = stringResource(R.string.subtitle))
    }
}