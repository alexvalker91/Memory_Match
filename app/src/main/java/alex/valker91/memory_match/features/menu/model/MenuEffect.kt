package alex.valker91.memory_match.features.menu.model

import alex.valker91.memory_match.model.Game

sealed interface MenuEffect {
    data class NavigateToGameScreen(val game: Game) : MenuEffect
    data object ShowToast : MenuEffect
}