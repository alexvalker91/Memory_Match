package alex.valker91.memory_match.features.menu.model

sealed interface MenuEvent {
    data object ClickOnPlayButton : MenuEvent
    data object InitializeGame : MenuEvent
}