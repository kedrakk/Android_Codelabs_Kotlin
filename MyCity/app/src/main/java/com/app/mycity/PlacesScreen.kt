package com.app.mycity

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.app.mycity.data.Category
import com.app.mycity.data.Places

@Composable
fun Places(category: Category, onPlaceItemClicked: (Places) -> Unit) {
    PlacesList(places = category.places, category = category, onPlaceItemClicked)
}

@Composable
fun PlacesList(
    places: List<Places>, category: Category, onPlaceItemClicked: (Places) -> Unit,
) {
    LazyColumn(
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        items(places, key = { place -> place.id }) { place ->
            PlaceItemList(
                place, category.name,
                onPlaceItemClicked
            )
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun PlaceItemList(places: Places, category: Int, onPlaceItemClicked: (Places) -> Unit) {
    Card(
        elevation = 2.dp,
        onClick = {
            onPlaceItemClicked(places)
        }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = 150.dp)
        ) {
            ImageItem(places.image)
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(top = 16.dp)
            ) {
                Text(
                    text = stringResource(category),
                    style = MaterialTheme.typography.h6,
                    modifier = Modifier.padding(8.dp)
                )
                Text(
                    text = stringResource(places.name),
                    style = MaterialTheme.typography.h5,
                    modifier = Modifier.padding(8.dp)
                )
            }
        }
    }
}

@Composable
fun PlaceDetail(
    selectedPlace: Places,
    onBackPressed: () -> Unit,
    modifier: Modifier = Modifier
) {
    BackHandler {
        onBackPressed()
    }
    Column(
        modifier = modifier.padding(4.dp)
    ) {
        Box {
            Image(
                painter = painterResource(selectedPlace.image),
                contentDescription = null,
                alignment = Alignment.TopCenter,
                contentScale = ContentScale.FillWidth
            )
            Text(
                text = stringResource(selectedPlace.name),
                style = MaterialTheme.typography.h5,
                color = MaterialTheme.colors.onSurface,
                modifier = Modifier
                    .padding(8.dp)
                    .align(Alignment.BottomStart)
            )
        }
        Text(
            text = stringResource(selectedPlace.desc),
            style = MaterialTheme.typography.body1,
            modifier = Modifier.padding(8.dp)
        )
    }
}