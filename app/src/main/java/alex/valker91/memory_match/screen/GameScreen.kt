package alex.valker91.memory_match.screen

import alex.valker91.memory_match.composable.ButtonScreen
import alex.valker91.memory_match.composable.GameCard
import alex.valker91.memory_match.composable.PlayButton
import alex.valker91.memory_match.composable.ScreenPreview
import alex.valker91.memory_match.composable.TextView
import alex.valker91.memory_match.model.Game
import alex.valker91.memory_match.ui.theme.Memory_MatchTheme
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import com.google.gson.Gson

@Composable
fun GameScreen(navController: NavController, game: Game) {
    Log.d("dfsdfsdf"," dazfsdfsdf ${game.gameNumber} sadfadf ${game.numberOfCoins}")
    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextView()
            TextView()
        }
        Spacer(modifier = Modifier.height(24.dp))
        ButtonScreen()
        Button(
            onClick = {
                val game = Game(3, 33)
                val gameJson = Gson().toJson(game)
                val numberOfAddingCoins = 99
                    navController.navigate("endGame" + "/${gameJson}" + "/${numberOfAddingCoins}")
            }
        ) {
            Text(
                text = "Navigate",
                color = MaterialTheme.colorScheme.onPrimary
            )
        }
    }
}

@ScreenPreview
@Composable
private fun GameScreenPreview() {
    Memory_MatchTheme {
        val mockNavController = rememberNavController()
        val game = Game(0, 0)
        GameScreen(mockNavController, game)
    }
}