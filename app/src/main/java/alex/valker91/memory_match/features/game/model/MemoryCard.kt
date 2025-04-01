package alex.valker91.memory_match.features.game.model

data class MemoryCard(
    var value: Int,
    var isBackDisplayed: Boolean = true,
    var matchFound: Boolean = false
) {
    fun flipCard(){
        isBackDisplayed = !isBackDisplayed
    }
}
