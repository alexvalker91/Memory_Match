package alex.valker91.memory_match.features.game.model

import alex.valker91.memory_match.model.Game

sealed interface GameState {
    data object Loading : GameState
    data class Ready(
        val game: Game = Game(0, 0),
        val gameStopWatch: GameStopWatch
    ) : GameState
    data class Error(val message: String) : GameState
}