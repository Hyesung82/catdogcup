package com.daejol.presentation.home

import androidx.lifecycle.ViewModel
import com.daejol.presentation.data.Animal
import com.daejol.presentation.data.HomeUiState
import com.daejol.presentation.data.SampleData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class HomeViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(HomeUiState(SampleData.animals[0]))
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    fun setBreed(breed: Animal) {
        _uiState.update { currentState ->
            currentState.copy(
                breed = breed
            )
        }
    }
}
