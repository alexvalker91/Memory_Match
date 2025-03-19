package alex.valker91.memory_match.features.end.ui

import alex.valker91.memory_match.composable.ScreenPreview
import alex.valker91.memory_match.model.Game
import alex.valker91.memory_match.ui.theme.Memory_MatchTheme
import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Composable
fun EndGameScreen(navController: NavController, game: Game, numberOfAddingCoins: Int) {
    Log.d("dfsd7fs8df"," dazfsdfsdf ${game.gameNumber} sadfadf ${game.numberOfCoins}")
    Log.d("dfsd7fs8df"," dazfsdfsdf ${numberOfAddingCoins} sadfadf ${numberOfAddingCoins}")
    BackHandler {
        navController.navigate("gameMenu") {
            // Очистка стека навигации до gameMenu
            popUpTo("gameMenu") { inclusive = true }
        }
    }
    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Hello"
        )
        Text(
            text = "Hello"
        )
        Button(
            onClick = {
                navController.navigate("gameMenu")
            }
        ) {
            Text(
                text = "Home",
                color = MaterialTheme.colorScheme.onPrimary // Цвет текста
            )
        }
    }
}

@ScreenPreview
@Composable
private fun EndGameScreenPreview() {
    Memory_MatchTheme {
        val mockNavController = rememberNavController()
        val game = Game(0, 0)
        EndGameScreen(mockNavController, game, 99)
    }
}