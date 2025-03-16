package alex.valker91.memory_match.manager

import alex.valker91.memory_match.model.Game
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

class GameManager constructor(
    @ApplicationContext private val context: Context
) {

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "data_store")

    fun getGameFlow(): Flow<Game> {
        return context.dataStore.data.map { preferences ->
            // Извлекаем значения с предоставлением значений по умолчанию, если они null
            val gameNumber = preferences[GAME_NUMBER] ?: 1 // Если null, используем 1
            val numberOfCoins = preferences[GAME_COIN] ?: 0 // Если null, используем 0
            // Возвращаем объект Game
            Game(gameNumber = gameNumber, numberOfCoins = numberOfCoins)
        }
    }

    suspend fun saveGame(game: Game) {
        context.dataStore.edit { preferences ->
            preferences[GAME_NUMBER] = game.gameNumber
            preferences[GAME_COIN] = game.numberOfCoins
        }
    }

    suspend fun getGame(): Game {
        val prefs = context.dataStore.data.first()
        // Извлекаем значения с предоставлением значений по умолчанию, если они null
        val gameNumber = prefs[GAME_NUMBER] ?: 1 // Если null, используем 1
        val numberOfCoins = prefs[GAME_COIN] ?: 0 // Если null, используем 0
        return Game(gameNumber = gameNumber, numberOfCoins = numberOfCoins)
    }

    companion object {
        private val GAME_NUMBER = intPreferencesKey("game_number")
        private val GAME_COIN = intPreferencesKey("game_coin")
    }
}