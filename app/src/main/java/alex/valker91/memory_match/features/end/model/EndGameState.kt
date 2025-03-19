package alex.valker91.memory_match.features.end.model

import alex.valker91.memory_match.model.Game

sealed interface EndGameState {
    data object Loading : EndGameState
    data class Ready(
        val game: Game = Game(0, 0),
        val numberOfAddingCoins: Int
    ) : EndGameState
    data class Error(val message: String) : EndGameState
}