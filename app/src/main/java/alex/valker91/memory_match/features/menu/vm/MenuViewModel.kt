package alex.valker91.memory_match.features.menu.vm

import alex.valker91.memory_match.features.end.model.EndGameState
import alex.valker91.memory_match.features.end.usecase.SaveGameUseCase
import alex.valker91.memory_match.features.menu.model.MenuEvent
import alex.valker91.memory_match.features.menu.model.MenuEffect
import alex.valker91.memory_match.features.menu.model.MenuState
import alex.valker91.memory_match.features.menu.usecase.GetGameUseCase
import alex.valker91.memory_match.model.Game
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MenuViewModel @Inject constructor(
    private val getGameUseCase: GetGameUseCase
) : ViewModel() {

    private val _state = MutableStateFlow<MenuState>(MenuState.Loading)
    val state: StateFlow<MenuState> = _state.asStateFlow()

    private val _effect = MutableSharedFlow<MenuEffect>()
    val effect: SharedFlow<MenuEffect> = _effect.asSharedFlow()

    init {
        onEvent(MenuEvent.InitializeGame)
    }

    fun onEvent(event: MenuEvent) {
        when (event) {
            MenuEvent.ClickOnPlayButton -> handlePlayButtonClick()
            MenuEvent.InitializeGame -> initializeGame()
        }
    }

    private fun handlePlayButtonClick() {
            when (val currentState = _state.value) {
                is MenuState.Ready -> {
                    val game = Game(
                        gameNumber = currentState.gameLevel,
                        numberOfCoins = currentState.countOfCoins
                    )
                    viewModelScope.launch {
                        _effect.emit(MenuEffect.NavigateToGameScreen(game))
                    }
                }
                is MenuState.Loading -> {
                }
                is MenuState.Error -> {
                }
            }
    }

    private fun initializeGame() {
//        viewModelScope.launch(Dispatchers.IO) {
//            val game: Game = Game(77, 7654321)
//            _state.value = MenuState.Ready(gameLevel = game.gameNumber, countOfCoins = game.numberOfCoins)
//        }

        viewModelScope.launch {
            val result = getGameUseCase.execute()
            if (result.isSuccess) {

                val game = result.getOrNull() // Получаем объект Game из Result
                if (game != null) {
                    _state.value = MenuState.Ready(result.getOrNull()?.gameNumber ?: 1, result.getOrNull()?.numberOfCoins ?: 0)
                }

//                _state.value = MenuState.Ready(result.game)
            } else if (result.isFailure) {
                val errorMessage = result.exceptionOrNull()?.message ?: "Unknown error"
                _state.value = MenuState.Error(errorMessage)
            }
        }
    }
}