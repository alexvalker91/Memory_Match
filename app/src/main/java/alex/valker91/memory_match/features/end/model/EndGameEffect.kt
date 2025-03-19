package alex.valker91.memory_match.features.end.model

sealed interface EndGameEffect {
    data object NavigateToMenuGameScreen : EndGameEffect
//    data object NavigateToBack : EndGameEffect
}