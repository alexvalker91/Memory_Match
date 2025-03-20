package alex.valker91.memory_match.features.game.vm

import alex.valker91.memory_match.features.game.model.GameEffect
import alex.valker91.memory_match.features.game.model.GameEvent
import alex.valker91.memory_match.features.game.model.GameState
import alex.valker91.memory_match.features.game.model.GameStopWatch
import alex.valker91.memory_match.model.Game
import android.os.CountDownTimer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
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

    private var stopWatch: CountDownTimer? = null

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
        // Устанавливаем начальное состояние Ready
        val initialStopWatch = GameStopWatch(currentSec = 0, currentCoin = 100)
        _state.value = GameState.Ready(game = game, gameStopWatch = initialStopWatch)


        // Запускаем таймер
        stopWatch = object : CountDownTimer(1000L * 60L * 60L, 1_000) { // Таймер на час
            override fun onTick(timer: Long) {
                // Обновляем состояние каждую секунду
                val currentState = _state.value
                if (currentState is GameState.Ready) {
                    val updatedStopWatch = currentState.gameStopWatch.copy(
                        currentSec = currentState.gameStopWatch.currentSec + 1, // Увеличиваем currentSec
                        currentCoin = setCoin(currentState.gameStopWatch.currentSec + 1, currentState.gameStopWatch.currentCoin)
                    )
                    _state.value = currentState.copy(gameStopWatch = updatedStopWatch)
                }
            }

            override fun onFinish() {
                // Таймер завершён, можно обработать событие
                viewModelScope.launch {
//                    _effect.emit(GameEffect.ShowTimerFinishedMessage("Timer finished!"))
                }
            }
        }
        stopWatch?.start()
    }

    private fun setCoin(sec: Long, coin: Int): Int {
        if (sec < 61) {
            return 100
        }
        print("QQQQQQQ"+(sec> 60)+"  "+(coin > 10))
        if ((sec > 60) and (coin > 10)) {
            return coin - 5
        }

        return 10
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