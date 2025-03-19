package alex.valker91.memory_match.features.end.vm

import alex.valker91.memory_match.features.end.model.EndGameEffect
import alex.valker91.memory_match.features.end.model.EndGameEvent
import alex.valker91.memory_match.features.end.model.EndGameState
import alex.valker91.memory_match.features.end.usecase.SaveGameUseCase
import alex.valker91.memory_match.features.game.model.GameEffect
import alex.valker91.memory_match.features.game.model.GameEvent
import alex.valker91.memory_match.features.game.model.GameState
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
class EndGameViewModel @Inject constructor(
    private val saveGameUseCase: SaveGameUseCase
) : ViewModel() {

    private val _state = MutableStateFlow<EndGameState>(EndGameState.Loading)
    val state: StateFlow<EndGameState> = _state.asStateFlow()

    private val _effect = MutableSharedFlow<EndGameEffect>()
    val effect: SharedFlow<EndGameEffect> = _effect.asSharedFlow()

    fun onEvent(event: EndGameEvent) {
        when (event) {
            is EndGameEvent.ClickOnNavigateEndButton -> {
                handleMenuButtonClick()
            }
            is EndGameEvent.InitializeGame -> {
                initializeGame(event.game, event.numberOfAddingCoins)
            }

            EndGameEvent.NavigateToBack -> {
                handleMenuButtonClick()
            }
        }
    }

    private fun initializeGame(game: Game, numberOfAddingCoins: Int) {
        viewModelScope.launch {
            val result = saveGameUseCase.execute(game, numberOfAddingCoins)
            if (result.isSuccess) {
                _state.value = EndGameState.Ready(game, numberOfAddingCoins)
            } else if (result.isFailure) {
                val errorMessage = result.exceptionOrNull()?.message ?: "Unknown error"
                _state.value = EndGameState.Error(errorMessage)
            }
        }
    }

    private fun handleMenuButtonClick() {
        viewModelScope.launch {
            _effect.emit(EndGameEffect.NavigateToMenuGameScreen)
        }
    }
}