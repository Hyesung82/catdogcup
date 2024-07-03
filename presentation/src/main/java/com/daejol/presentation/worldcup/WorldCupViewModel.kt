package com.daejol.presentation.worldcup

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import entity.ImageEntity
import kotlinx.coroutines.launch
import okhttp3.internal.toImmutableList
import usecase.GetImageUsecase
import usecase.WorldCupType
import javax.inject.Inject

@HiltViewModel
class WorldCupViewModel @Inject constructor(
    val imageUsecase: GetImageUsecase
) : ViewModel() {
    private val _worldCupType = mutableStateOf(WorldCupType.CAT)
    val worldCupType: State<WorldCupType> = _worldCupType

    private val _worldCupLevel = mutableStateOf("16강")
    val worldCupLevel: State<String> = _worldCupLevel

    private val _imageType = mutableStateOf("무작위")
    val imageType: State<String> = _imageType

    private val _bookmark = mutableStateOf(true)
    val bookmark: State<Boolean> = _bookmark

    private val _share = mutableStateOf(true)
    val share: State<Boolean> = _share

    private val _currentGameLevel = mutableIntStateOf(0)
    val currentGameLevel: State<Int> = _currentGameLevel

    private val _worldCupAnimalList = mutableStateOf(listOf<ImageEntity>())
    val worldCupAnimalList: State<List<ImageEntity>> = _worldCupAnimalList

    fun setType(type: WorldCupType) {
        _worldCupType.value = type
    }

    fun setLevel(level: String) {
        _worldCupLevel.value = level
    }

    fun setImageSelection(imageType: String) {
        _imageType.value = imageType
    }

    fun saveBookmark(save: Boolean) {
        _bookmark.value = save
    }

    fun allowShare(share: Boolean) {
        _share.value = share
    }

    fun updateGameLevel(level: Int) {
        _currentGameLevel.intValue = level
    }

    fun getAnimalList() {
        viewModelScope.launch {
            imageUsecase.getAnimalList(
                type = _worldCupType.value,
                randomImageCount = currentGameLevel.value / 2
            )
                .collect {
                    val list = it.toImmutableList()
                    _worldCupAnimalList.value = list
                }
        }
    }
}