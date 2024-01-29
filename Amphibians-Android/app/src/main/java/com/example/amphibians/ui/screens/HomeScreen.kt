package com.example.amphibians.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.amphibians.model.Amphibians
import com.example.amphibians.viewModel.AmphibianUIState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(amphibianUIState: AmphibianUIState) {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = "Amphibians") })
        }
    ) {
        Box(
            modifier = Modifier
                .padding(it)
                .padding(horizontal = 20.dp)
        ) {
            when (amphibianUIState) {
                is AmphibianUIState.Loading -> LoadingScreen()
                is AmphibianUIState.Success -> AmphibianList(amphibiansList = amphibianUIState.amphibianList)
                else -> ErrorScreen {

                }
            }
        }
    }
}

@Composable
fun AmphibianList(amphibiansList: List<Amphibians>) {
    LazyColumn() {
        items(items = amphibiansList, key = {
            it.name
        }) {
            AmphibianCard(amphibians = it)
        }
    }
}

@Composable
fun AmphibianCard(amphibians: Amphibians) {
    Box(modifier = Modifier.padding(vertical = 5.dp)) {
        Card {
            Column(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = amphibians.name,
                    modifier = Modifier
                        .padding(vertical = 15.dp)
                        .fillMaxWidth(),
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
                AsyncImage(
                    model = amphibians.imgSrc,
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth(),
                    contentScale = ContentScale.FillWidth
                )
                Text(
                    text = amphibians.description,
                    modifier = Modifier.padding(vertical = 10.dp, horizontal = 10.dp),
                    textAlign = TextAlign.Justify,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Medium
                )
            }
        }
    }
}