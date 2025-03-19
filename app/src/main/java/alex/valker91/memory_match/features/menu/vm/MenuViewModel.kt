package alex.valker91.memory_match.features.menu.vm

import alex.valker91.memory_match.features.menu.model.MenuEvent
import alex.valker91.memory_match.features.menu.model.MenuEffect
import alex.valker91.memory_match.features.menu.model.MenuState
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
class MenuViewModel @Inject constructor() : ViewModel() {

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
        viewModelScope.launch(Dispatchers.IO) {
            val game: Game = Game(77, 7654321)
            _state.value = MenuState.Ready(gameLevel = game.gameNumber, countOfCoins = game.numberOfCoins)
        }
    }
}