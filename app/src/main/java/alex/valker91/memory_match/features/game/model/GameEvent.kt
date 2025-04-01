package alex.valker91.memory_match.features.game.model

import alex.valker91.memory_match.model.Game

sealed interface GameEvent {
    data object ClickOnNavigateEndButton : GameEvent
    data class InitializeGame(val game: Game) : GameEvent
    data class ClickOnCard(val cardId: Int) : GameEvent
}