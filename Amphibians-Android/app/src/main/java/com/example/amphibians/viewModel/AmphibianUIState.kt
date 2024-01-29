package com.example.amphibians.viewModel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.amphibians.AmphibiansApplication
import com.example.amphibians.data.IAppRepository
import com.example.amphibians.model.Amphibians
import kotlinx.coroutines.launch

sealed interface AmphibianUIState {
    data class Success(val amphibianList: List<Amphibians>) : AmphibianUIState
    object Error : AmphibianUIState
    object Loading : AmphibianUIState
}

class AmphibianViewModel(private val repository: IAppRepository) : ViewModel() {

    var amphibianUIState: AmphibianUIState by mutableStateOf(AmphibianUIState.Loading)
        private set

    init {
        getAllAmphibians()
    }

    fun getAllAmphibians() {
        viewModelScope.launch {
            amphibianUIState = AmphibianUIState.Loading
            amphibianUIState = try {
                AmphibianUIState.Success(repository.getAllAmphibians())
            } catch (exp: Exception) {
                Log.e("Error",exp.message.toString())
                AmphibianUIState.Error
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application =
                    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as AmphibiansApplication)
                val repository = application.retrofitInstance.appRepository
                AmphibianViewModel(repository = repository)
            }
        }
    }
}
