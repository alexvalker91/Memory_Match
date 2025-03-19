package alex.valker91.memory_match.features.end.usecase

import alex.valker91.memory_match.manager.GameManager
import alex.valker91.memory_match.model.Game
import javax.inject.Inject

class SaveGameUseCase @Inject constructor(
    private val gameManager: GameManager
) {

    suspend fun execute(game: Game, numberOfAddingCoins: Int): Result<Unit> {
        return try {
            val newGame: Game = Game(game.gameNumber + 1, game.numberOfCoins +numberOfAddingCoins)
            gameManager.saveGame(newGame)
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}