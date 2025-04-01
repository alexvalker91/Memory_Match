package alex.valker91.memory_match.features.game.model

import alex.valker91.memory_match.model.Game

sealed interface GameState {
    data object Loading : GameState
    data class Ready(
        val game: Game = Game(0, 0),
        val gameStopWatch: GameStopWatch,

        val cards: Array<MemoryCard> = generateCardsArray(10),
        val card1: Int? = null,
        val card2: Int? = null,
        val pairCount: Int = 10,
        val pairsMatched: Int = 0,
        val clickCount: Int = 0,

    ) : GameState
    data class Error(val message: String) : GameState
}

fun generateCardsArray(matches: Int): Array<MemoryCard> {
    val singles = 1..matches // [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]
    val doubles = singles + singles // [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10]
    return doubles.shuffled().map { MemoryCard(it) }.toTypedArray()
}
// doubles.shuffled() = [3, 7, 1, 10, 5, 2, 6, 9, 4, 8, 7, 1, 9, 6, 2, 5, 10, 3, 8, 4]