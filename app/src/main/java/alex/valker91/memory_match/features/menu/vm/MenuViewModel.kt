package alex.valker91.memory_match.features.menu.vm

import alex.valker91.memory_match.features.menu.model.MainEvent
import alex.valker91.memory_match.features.menu.model.MenuEffect
import alex.valker91.memory_match.features.menu.model.MenuState
import alex.valker91.memory_match.model.Game
import alex.valker91.memory_match.navigation.NavRoutes
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
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
//        getDataFromSharedPreference()
        onEvent(MainEvent.InitializeGame)
    }

    fun onEvent(event: MainEvent) {
        when (event) {
            MainEvent.ClickOnPlayButton -> handlePlayButtonClick()
            MainEvent.InitializeGame -> initializeGame()
        }
    }

    private fun handlePlayButtonClick() {
//        viewModelScope.launch {
            when (val currentState = _state.value) {
                is MenuState.Ready -> {
                    val game = Game(
                        gameNumber = currentState.gameLevel,
                        numberOfCoins = currentState.countOfCoins
                    )
                    viewModelScope.launch {
//                        _effect.emit(MenuEffect.ShowToast("Game ${game.gameNumber} started!"))
                        _effect.emit(MenuEffect.NavigateToGameScreen(game))
                    }
                    // Берем game из текущего состояния
//                    _effect.emit(MenuEffect.NavigateToGameScreen(Game(currentState.gameLevel, currentState.countOfCoins)))
                }
                is MenuState.Loading -> {
                    // Можно показать загрузку или проигнорировать нажатие
                }
                is MenuState.Error -> {
//                    _effect.emit(MenuEffect.ShowToast)
                }
            }
//        }
//        viewModelScope.launch {
//            _effect.emit(MenuEffect.NavigateToGameScreen(game))
//        }
    }

    private fun initializeGame() {
        viewModelScope.launch(Dispatchers.IO) {
            val game: Game = Game(3, 33123)
            _state.value = MenuState.Ready(gameLevel = game.gameNumber, countOfCoins = game.numberOfCoins)
        }
    }
}