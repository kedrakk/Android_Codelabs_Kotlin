package com.app.mycity

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
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
fun MyCityScreen(){
    val viewModel: MyCityViewModel = viewModel()
    val uiState by viewModel.uiState.collectAsState()

    var appbarTitle = when(uiState.currentPage){
        CurrentPage.SUB -> {
            stringResource(id = uiState.selectedCategory.name)
        }
        else -> {
            stringResource(id = R.string.app_name)
        }
    }
    Scaffold(
        topBar = {
            MyCityTopBar(
                title = appbarTitle
            )
        }
    ) {

        if(uiState.currentPage==CurrentPage.SUB){
            Places(category = uiState.selectedCategory)
        }
        else{
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
    title:String
){
    TopAppBar{
         Text(
             text = title,
             style = Typography.h2
         )
    }
}

@Composable
fun CategoryList(
    categoryItems:List<Category>,
    onItemClick: (Category) -> Unit,
){
    LazyColumn(
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        items(categoryItems, key = { category -> category.id }) { category ->
            CategoryItemList(
                category,onItemClick
            )
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CategoryItemList(
    category: Category,
    onItemClick: (Category) -> Unit,
){
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
internal fun ImageItem(imageId:Int, modifier: Modifier = Modifier) {
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