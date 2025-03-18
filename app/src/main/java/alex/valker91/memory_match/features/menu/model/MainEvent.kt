package alex.valker91.memory_match.features.menu.model

sealed interface MainEvent {
    data object ClickOnPlayButton : MainEvent
    data object InitializeGame : MainEvent
}