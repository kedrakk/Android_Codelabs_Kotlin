package com.app.mycity

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.app.mycity.data.Category
import com.app.mycity.ui.theme.MyCityViewModel
import com.app.mycity.ui.theme.Typography
import com.app.mycity.utils.CurrentPage

@Composable
fun MyCityScreen() {
    val viewModel: MyCityViewModel = viewModel()
    val uiState by viewModel.uiState.collectAsState()

    var isShowBackButtonOnAppBar: Boolean = false
    var appbarTitle: String = stringResource(id = uiState.selectedCategory.name)

    when (uiState.currentPage) {
        CurrentPage.SUB -> {
            appbarTitle = stringResource(id = uiState.selectedCategory.name)
            isShowBackButtonOnAppBar = true
        }
        CurrentPage.DETAIL -> {
            appbarTitle = stringResource(id = uiState.selectedPlaces.name)
            isShowBackButtonOnAppBar = true
        }
        else -> {
            appbarTitle = stringResource(id = R.string.app_name)
            isShowBackButtonOnAppBar = false
        }
    }
    Scaffold(
        topBar = {
            MyCityTopBar(
                title = appbarTitle,
                isShowBackButton = isShowBackButtonOnAppBar,
                onBackButtonClick = {
                    if (uiState.currentPage == CurrentPage.SUB) {
                        viewModel.navigateToMainPage()
                    }
                    if(uiState.currentPage==CurrentPage.DETAIL){
                        viewModel.navigateToItemList()
                    }
                }
            )
        }
    ) {

        if (uiState.currentPage == CurrentPage.SUB) {
            Places(
                category = uiState.selectedCategory,
                onPlaceItemClicked = {
                    viewModel.updateCurrentItem(it)
                    viewModel.navigateToDetail()
                },
            )
        }
        else if(uiState.currentPage == CurrentPage.DETAIL){
            PlaceDetail(
                selectedPlace = uiState.selectedPlaces,
                onBackPressed = { /*TODO*/ }
            )
        }
        else {
            CategoryList(
                categoryItems = uiState.categoryList,
                onItemClick = {
                    viewModel.updateCurrentCategory(it)
                    viewModel.navigateToItemList()
                }
            )
        }
    }
}

@Composable
fun MyCityTopBar(
    title: String,
    isShowBackButton: Boolean,
    onBackButtonClick: () -> Unit
) {
    TopAppBar {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (isShowBackButton)
                IconButton(onClick = onBackButtonClick) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.back_button)
                    )
                } else {
                null
            }
            Text(
                text = title,
                style = Typography.h2
            )
        }
    }
}

@Composable
fun CategoryList(
    categoryItems: List<Category>,
    onItemClick: (Category) -> Unit,
) {
    LazyColumn(
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        items(categoryItems, key = { category -> category.id }) { category ->
            CategoryItemList(
                category, onItemClick
            )
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CategoryItemList(
    category: Category,
    onItemClick: (Category) -> Unit,
) {
    Card(
        elevation = 2.dp,
        onClick = {
            onItemClick(category)
        }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = 150.dp)
        ) {
            ImageItem(category.image)
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(top = 16.dp)
            ) {
                Text(
                    text = stringResource(category.name),
                    style = MaterialTheme.typography.h5,
                    modifier = Modifier.padding(8.dp)

                )
            }
        }
    }
}

@Composable
internal fun ImageItem(imageId: Int, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .width(170.dp)
            .fillMaxHeight()
    ) {
        Image(
            painter = painterResource(imageId),
            contentDescription = null,
            alignment = Alignment.TopCenter,
            contentScale = ContentScale.FillWidth
        )
    }
}