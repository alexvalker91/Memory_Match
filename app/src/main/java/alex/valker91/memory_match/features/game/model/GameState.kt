package alex.valker91.memory_match.features.game.model

sealed interface GameState {
    data object Loading : GameState
    data object Ready : GameState
    data class Error(val message: String) : GameState
}