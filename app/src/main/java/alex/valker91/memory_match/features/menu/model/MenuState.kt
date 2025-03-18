package alex.valker91.memory_match.features.menu.model

sealed interface MenuState {
    data object Loading : MenuState
    data class Ready(
        val gameLevel: Int = 0,
        val countOfCoins: Int = 0
    ) : MenuState
    data class Error(val message: String) : MenuState
}