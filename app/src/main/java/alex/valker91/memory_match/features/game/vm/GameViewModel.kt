package alex.valker91.memory_match.features.game.vm

import alex.valker91.memory_match.features.game.model.GameEffect
import alex.valker91.memory_match.features.game.model.GameEvent
import alex.valker91.memory_match.features.game.model.GameState
import alex.valker91.memory_match.features.menu.model.MenuEffect
import alex.valker91.memory_match.features.menu.model.MenuState
import alex.valker91.memory_match.model.Game
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GameViewModel @Inject constructor() : ViewModel() {

    private val _state = MutableStateFlow<GameState>(GameState.Loading)
    val state: StateFlow<GameState> = _state.asStateFlow()

    private val _effect = MutableSharedFlow<GameEffect>()
    val effect: SharedFlow<GameEffect> = _effect.asSharedFlow()

    fun onEvent(event: GameEvent) {
        when (event) {
            is GameEvent.ClickOnNavigateEndButton -> {
                handlePlayButtonClick()
            }
            is GameEvent.InitializeGame -> {
                initializeGame(event.game)
            }
        }
    }

    private fun initializeGame(game: Game) {
        _state.value = GameState.Ready(game)
    }

    private fun handlePlayButtonClick() {
        when (val currentState = _state.value) {
            is GameState.Ready -> {
                val game: Game = currentState.game
                viewModelScope.launch {
                    _effect.emit(GameEffect.NavigateToGameScreen(game, 10))
                }
            }
            is GameState.Loading -> {
            }
            is GameState.Error -> {
            }
        }
    }
}