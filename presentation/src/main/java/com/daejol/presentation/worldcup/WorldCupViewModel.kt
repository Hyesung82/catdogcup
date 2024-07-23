package com.daejol.presentation.worldcup

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import entity.ImageEntity
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import okhttp3.internal.toImmutableList
import usecase.GetImageUsecase
import usecase.WorldCupType
import javax.inject.Inject
import kotlin.math.pow
import kotlin.math.sqrt
import kotlin.random.Random

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

    private val _currentGameLevel = mutableIntStateOf(16)
    val currentGameLevel: State<Int> = _currentGameLevel

    private val _worldCupAnimalList = mutableStateOf(listOf<ImageEntity>())

    private val _currentMatchImages = mutableStateOf(listOf<ImageEntity>())
    val currentMatchImages: State<List<ImageEntity>> = _currentMatchImages

    // 128 -> 64 -> 32 -> 16 -> 4 -> final
    private val _gameProgressImageList =
        mutableStateOf<MutableList<MutableList<ImageEntity>>>(mutableListOf())

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

    fun getAnimalList(
        onFinish: () -> Unit
    ) {
        viewModelScope.launch {
            imageUsecase.getAnimalList(
                type = _worldCupType.value, randomImageCount = currentGameLevel.value
            ).catch {

            }.collect {
                val list = it.toImmutableList()
                _worldCupAnimalList.value = list
                onFinish.invoke()
            }
        }
    }

    fun initializeGame() {
        if (_gameProgressImageList.value.isEmpty() || _gameProgressImageList.value.last().size != 0) {
            var gameLevel = _currentGameLevel.intValue * 2
            _gameProgressImageList.value.removeAll { true }

            while (gameLevel % 2 == 0) {
                _gameProgressImageList.value.add(mutableListOf())
                gameLevel /= 2
            }

            val tempList = _worldCupAnimalList.value.toMutableList()
            while (_gameProgressImageList.value.first().size < _worldCupAnimalList.value.size) {
                val random = Random.nextInt(tempList.size)
                val element = tempList[random]
                _gameProgressImageList.value.first().add(element)
                tempList.remove(element)
            }

            // 다음 대결 상대 정하기
            var element = _gameProgressImageList.value.first()
            _currentMatchImages.value = element.toList()
        }
    }

    // 현재 누른 이미지는 [_gameProgressImageList] 에 넣고 패배한 이미지는 사라진다.
    fun updateMatch(
        winner: Int,
        onMatchEnd: () -> Unit
    ) {
        println("[keykat] -------------------------------------------------")
        _gameProgressImageList.value.forEach { it ->
            println("[keykat] 현재 게임 상황: _gameProgressImageList: ${it.size}")
        }

        removeSelectedMatch(winner)
        setNextMatchedImages()

        if (checkEndGame(onMatchEnd)) {
            return
        }

        println("[keykat] -------------------------------------------------")
        _gameProgressImageList.value.forEach { it ->
            println("[keykat] 이후 게임 상황: _gameProgressImageList: ${it.size}")
        }
    }

    private fun checkEndGame(onMatchEnd: () -> Unit): Boolean {
        if (_gameProgressImageList.value.last().size != 0) {
            _gameProgressImageList.value.forEach { it ->
                println("[keykat] checlEndGame: _gameProgressImageList: ${it}")
            }

            _currentMatchImages.value = listOf(_gameProgressImageList.value.last().last())
            onMatchEnd.invoke()
            return true
        }

        return false
    }

    private fun removeSelectedMatch(winner: Int) {
        for (i in 0..<_gameProgressImageList.value.size) {
            val element = _gameProgressImageList.value[i]
            if (element.size >= 2) {
                // 일단 현재 이미지가 리스트 내 어떤 이미지인지 판단하고
                val first = element.removeFirst()
                val second = element.removeFirst()

                // 승자조에 넣기
                if (winner == 0) {
                    _gameProgressImageList.value[i + 1].add(first.copy())
                } else {
                    _gameProgressImageList.value[i + 1].add(second.copy())
                }

                break
            }
        }
    }

    private fun setNextMatchedImages() {
        for (i in 0..<_gameProgressImageList.value.size) {
            val element = _gameProgressImageList.value[i]
            // 결과가 나왔을 땐 다음 게임 정보로 업데이트할 필요 없음
            if (element.size >= 2) {
                // 128 -> 64 -> 32 -> 16 -> 8 -> 4 -> final
                println("[keykat] ${(i)}강")
                _currentGameLevel.intValue =
                    (2.0).pow((_gameProgressImageList.value.size - i - 1).toDouble()).toInt()
                if (_currentGameLevel.value == 2) {
                    _worldCupLevel.value = "결승"
                } else {
                    _worldCupLevel.value = "${_currentGameLevel.intValue}강"
                }

                _currentMatchImages.value = element.toList()

                return
            }
        }
    }
}
