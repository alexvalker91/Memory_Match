package alex.valker91.memory_match.features.end.model

import alex.valker91.memory_match.model.Game

sealed interface EndGameEvent {
    data object ClickOnNavigateEndButton : EndGameEvent
    data object NavigateToBack : EndGameEvent
    data class InitializeGame(val game: Game, val numberOfAddingCoins: Int) : EndGameEvent
}