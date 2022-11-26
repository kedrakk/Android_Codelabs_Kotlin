package com.app.mycity.ui.theme

import androidx.lifecycle.ViewModel
import com.app.mycity.data.Category
import com.app.mycity.data.CategoryRepo
import com.app.mycity.utils.CurrentPage
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class MyCityViewModel: ViewModel() {
    private val _uiState = MutableStateFlow(
        MyCityUIState(
            categoryList = CategoryRepo.CategoryData(),
            selectedCategory = CategoryRepo.CategoryData().getOrElse(0){
                CategoryRepo.defaultCategory
            },
            currentPage = CurrentPage.MAIN
        )
    )
    val uiState: StateFlow<MyCityUIState> = _uiState

    fun updateCurrentCategory(selectedCategory: Category) {
        _uiState.update {
            it.copy(selectedCategory = selectedCategory)
        }
    }

    fun navigateToItemList() {
        _uiState.update {
            it.copy(currentPage = CurrentPage.SUB)
        }
    }

    fun navigateToMainPage(){
        _uiState.update {
            it.copy(currentPage = CurrentPage.MAIN)
        }
    }

}

data class MyCityUIState(
    val categoryList: List<Category>,
    val selectedCategory: Category,
    val currentPage: CurrentPage
)