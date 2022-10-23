package com.app.superhero

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.app.superhero.data.SuperHeroData
import com.app.superhero.model.SuperHero
import com.app.superhero.ui.theme.Shapes
import com.app.superhero.ui.theme.SuperHeroTheme
import com.app.superhero.ui.theme.typography

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SuperHeroTheme(
                darkTheme = true
            ) {
                Surface{
                    SuperHeroApp()
                }
            }
        }
    }
}

@Composable
fun SuperHeroApp() {
    Scaffold(
        topBar={
            SuperHeroAppBar()
        },
        modifier = Modifier.fillMaxSize()
    ) {
        SuperHeroList(allSuperHero = SuperHeroData.allSuperHeroes)
    }
}

@Composable
fun SuperHeroAppBar(){
    Box(
        modifier = Modifier.padding(10.dp)
            .fillMaxWidth(),
        contentAlignment = Alignment.Center
    ){
        Text(
            text = stringResource(id = R.string.app_name),
            style = typography.h1,
        )
    }
}

@Composable
fun SuperHeroList(allSuperHero:List<SuperHero>){
    LazyColumn(
        modifier = Modifier.padding(15.dp)
    ){
        items(allSuperHero){
            SuperHeroItem(superHero = it)
        }
    }
}

@Composable
fun SuperHeroItem(superHero: SuperHero){
    Card(
        elevation = 2.dp,
        modifier = Modifier.padding(vertical = 5.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .height(74.dp)
                .clip(Shapes.large)
        ) {
            Column(
                modifier = Modifier.weight(3f)
            ) {
                Text(
                    text = stringResource(id = superHero.name),
                    style = typography.h3
                )
                Text(
                    text = stringResource(id = superHero.description),
                    style = typography.body1
                )
            }
            Image(
                painter = painterResource(id = superHero.image),
                contentDescription = stringResource(id = superHero.name),
                modifier = Modifier
                    .padding(start = 16.dp)
                    .clip(Shapes.small)
                    .weight(1f)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    SuperHeroTheme {
        SuperHeroApp()
    }
}