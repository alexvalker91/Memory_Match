package alex.valker91.memory_match.features.menu.usecase

import alex.valker91.memory_match.manager.GameManager
import alex.valker91.memory_match.model.Game
import javax.inject.Inject

class GetGameUseCase @Inject constructor(
    private val gameManager: GameManager
) {

    suspend fun execute(): Result<Game> {
        return try {
            val game: Game = gameManager.getGame()
            Result.success(game)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}