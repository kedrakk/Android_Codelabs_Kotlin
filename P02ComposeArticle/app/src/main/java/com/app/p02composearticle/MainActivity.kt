package com.app.p02composearticle

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.p02composearticle.ui.theme.P02ComposeArticleTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            P02ComposeArticleTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    ComposeArticle(
                        title = stringResource(R.string.compose_title),
                        body1 = stringResource(R.string.compose_body1),
                        body2 = stringResource(R.string.compose_body2)
                    )
                }
            }
        }
    }
}

@Composable
fun ComposeArticle(title: String,body1:String,body2:String) {
    Column {
        Image(
            painter = painterResource(id = R.drawable.bg_compose_background),
            contentDescription = null,
            modifier = Modifier.fillMaxWidth()
        )
        Text(
            text = title,
            fontSize = 24.sp,
            modifier = Modifier.padding(
                start = 16.dp, end = 16.dp, top = 16.dp, bottom = 16.dp
            )
        )
        Text(text = body1, modifier = Modifier.padding(start = 16.dp, end = 16.dp), textAlign = TextAlign.Justify)
        Text(text = body2,modifier = Modifier.padding(start = 16.dp, end = 16.dp,top = 16.dp, bottom = 16.dp), textAlign = TextAlign.Justify)
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    P02ComposeArticleTheme {
        ComposeArticle(
            title = stringResource(R.string.compose_title),
            body1 = stringResource(R.string.compose_body1),
            body2 = stringResource(R.string.compose_body2)
        )
    }
}