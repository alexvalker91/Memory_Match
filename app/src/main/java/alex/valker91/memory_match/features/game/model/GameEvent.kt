package alex.valker91.memory_match.features.game.model

import alex.valker91.memory_match.features.menu.model.MainEvent

sealed interface GameEvent {
    data object ClickOnNavigateEndButton : GameEvent
}