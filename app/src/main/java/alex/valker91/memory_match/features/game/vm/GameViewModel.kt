package alex.valker91.memory_match.features.game.vm

import alex.valker91.memory_match.features.game.model.GameEffect
import alex.valker91.memory_match.features.game.model.GameEvent
import alex.valker91.memory_match.features.game.model.GameState
import alex.valker91.memory_match.features.game.model.GameStopWatch
import alex.valker91.memory_match.model.Game
import android.os.CountDownTimer
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
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

    private var delayedCompareJob: Job? = null
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

            is GameEvent.ClickOnCard -> {
                onCardClick(event.cardId)
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

    private fun onCardClick(id: Int){
        cancelPreviousJob()
        increaseClickCount()

        var flipJob: Job? = null

        when (val currentState = _state.value) {
            is GameState.Ready -> {
                val cards = currentState.cards
                if(cards[id].isBackDisplayed) {
                    flipJob = viewModelScope.launch(Dispatchers.IO) {
                        flip(id)
                    }
                }
            }
            is GameState.Loading -> {
            }
            is GameState.Error -> {
            }
        }

        when (val currentState = _state.value) {
            is GameState.Ready -> {
                val cards = currentState.cards
                if(cards[id].isBackDisplayed){
                    delayedCompareJob = viewModelScope.launch (Dispatchers.IO) {
                        flipJob?.join()
//                        flip(id)
                        when (val currentState = _state.value) {
                            is GameState.Ready -> {
                                Log.d("LogCat12345", "AAAAAA ${currentState.card1} + ${currentState.card2}")
                                val firstIndex = currentState.card1
                                val secondIndex = currentState.card2
                                val bothCardsAreNotNull = firstIndex != null && secondIndex != null
                                val cardsMatchSkipDelay = if(bothCardsAreNotNull) cards[firstIndex!!].value == cards[secondIndex!!].value
                                else false
                                if(!cardsMatchSkipDelay){
                                    delay(2000)
                                }
                                compareValues(firstIndex, secondIndex)
                            }
                            else ->{}
                        }


                    }
                }
            }
            is GameState.Loading -> {
            }
            is GameState.Error -> {
            }
        }
    }

    private fun flip(id: Int) {
        when (val currentState = _state.value) {
            is GameState.Ready -> {

                val cards = currentState.cards.copyOf()
                cards[id].flipCard()
                val card2 = currentState.card1
                _state.value = currentState.copy(
                    card1 = id,
                    card2 = card2,
                    cards = cards
                )
            }
            is GameState.Loading -> {
            }
            is GameState.Error -> {
            }
        }
    }

    private fun cancelPreviousJob(){
        Log.d("LogCat123", "fun cancelPreviousJob")
        when (val currentState = _state.value) {
            is GameState.Ready -> {
                Log.d("LogCat123", "fun cancelPreviousJob Ready")
                val firstIndex = currentState.card1
                val secondIndex = currentState.card2
                if(delayedCompareJob != null){
                    Log.d("LogCat123", "fun cancelPreviousJob Ready delayedCompareJob != null")
                    delayedCompareJob?.cancel()
                    compareValues(firstIndex, secondIndex)
                }
            }
            is GameState.Loading -> {
            }
            is GameState.Error -> {
            }
        }
    }

    private fun increaseClickCount(){
        when (val currentState = _state.value) {
            is GameState.Ready -> {
                _state.value = currentState.copy(
                    clickCount = currentState.clickCount + 1
                )
            }
            is GameState.Loading -> {
            }
            is GameState.Error -> {
            }
        }
    }

    private fun compareValues(first: Int?, second: Int?) {
        Log.d("LogCat123","first:$first second:$second")
        when (val currentState = _state.value) {
            is GameState.Ready -> {
                val cards = currentState.cards.copyOf()
                if(second != null && first != null){
                    val card1 = cards[first]
                    val card2 = cards[second]
                    if(card1.value != card2.value){
                        cards[first].flipCard()
                        cards[second].flipCard()
                    }else{
                        cards[first].matchFound = true
                        cards[second].matchFound = true
                        _state.value = currentState.copy(
                            cards = cards,
                            pairsMatched = currentState.pairsMatched + 1
                        )
                    }
                }
            }
            is GameState.Loading -> {
            }
            is GameState.Error -> {
            }
        }

        isGuessedAllCards()
        resetCompareCards()
    }

    private fun isGuessedAllCards() {
        when (val currentState = _state.value) {
            is GameState.Ready -> {
                if (currentState.pairCount == currentState.pairsMatched){
                    viewModelScope.launch (Dispatchers.IO) {
                        val game: Game = currentState.game
                        _effect.emit(GameEffect.NavigateToGameScreen(game, 10))
                    }
                }
            }
            is GameState.Loading -> {
            }
            is GameState.Error -> {
            }
        }
    }

    private fun resetCompareCards(){
        when (val currentState = _state.value) {
            is GameState.Ready -> {
                if(currentState.card2 != null){
                    _state.value = currentState.copy(card1 = null, card2 = null)
                }
            }
            is GameState.Loading -> {
            }
            is GameState.Error -> {
            }
        }
    }
}