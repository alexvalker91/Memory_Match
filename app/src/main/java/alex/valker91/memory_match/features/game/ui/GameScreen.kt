package alex.valker91.memory_match.features.game.ui

import alex.valker91.memory_match.composable.ButtonScreen
import alex.valker91.memory_match.composable.ScreenPreview
import alex.valker91.memory_match.composable.TextView
import alex.valker91.memory_match.features.game.model.GameEffect
import alex.valker91.memory_match.features.game.model.GameEvent
import alex.valker91.memory_match.features.game.model.GameState
import alex.valker91.memory_match.features.game.vm.GameViewModel
import alex.valker91.memory_match.model.Game
import alex.valker91.memory_match.navigation.NavRoutes
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.google.gson.Gson

@Composable
fun GameScreen(
    navController: NavController,
    game: Game,
    viewModel: GameViewModel = hiltViewModel()
) {
    val viewState by viewModel.state.collectAsStateWithLifecycle()
    LaunchedEffect(viewState) {
        Log.d("Logdfgdfgdfs1gCa1t", "OperatorDepositScreen STATE: $viewState")
    }

    LaunchedEffect(game) {
        viewModel.onEvent(GameEvent.InitializeGame(game)) // Отправляем событие для инициализации
    }

    LaunchedEffect(Unit) {
        viewModel.effect.collect { effect ->
            when (effect) {
                is GameEffect.NavigateToGameScreen -> {
                    val gameJson = Gson().toJson(effect.game)
                    navController.navigate(NavRoutes.EndGame.route + "/${gameJson}" + "/${effect.addingCoins}")
                }
            }
        }
    }

    when (val state = viewState) {
        is GameState.Error -> {}
        GameState.Loading -> {}
        is GameState.Ready -> {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
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
                        viewModel.onEvent(GameEvent.ClickOnNavigateEndButton)
                    }
                ) {
                    Text(
                        text = "Navigate",
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                }
            }
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