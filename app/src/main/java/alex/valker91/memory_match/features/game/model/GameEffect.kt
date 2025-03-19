package alex.valker91.memory_match.features.game.model

import alex.valker91.memory_match.model.Game

sealed interface GameEffect {
    data class NavigateToGameScreen(val game: Game, val addingCoins: Int) : GameEffect
}