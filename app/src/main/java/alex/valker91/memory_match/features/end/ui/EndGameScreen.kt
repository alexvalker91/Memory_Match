package alex.valker91.memory_match.features.end.ui

import alex.valker91.memory_match.composable.ScreenPreview
import alex.valker91.memory_match.features.end.model.EndGameEffect
import alex.valker91.memory_match.features.end.model.EndGameEvent
import alex.valker91.memory_match.features.end.model.EndGameState
import alex.valker91.memory_match.features.end.vm.EndGameViewModel
import alex.valker91.memory_match.features.game.model.GameEffect
import alex.valker91.memory_match.features.game.model.GameEvent
import alex.valker91.memory_match.features.game.vm.GameViewModel
import alex.valker91.memory_match.model.Game
import alex.valker91.memory_match.navigation.NavRoutes
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
fun EndGameScreen(navController: NavController, game: Game, numberOfAddingCoins: Int, viewModel: EndGameViewModel = hiltViewModel()) {

    val viewState by viewModel.state.collectAsStateWithLifecycle()
    LaunchedEffect(viewState) {
        Log.d("Logdfgdfgdfs1gCa1t", "OperatorDepositScreen STATE: $viewState")
    }

    LaunchedEffect(game) {
        viewModel.onEvent(EndGameEvent.InitializeGame(game, numberOfAddingCoins)) // Отправляем событие для инициализации
    }

    LaunchedEffect(Unit) {
        viewModel.effect.collect { effect ->
            when (effect) {
                is EndGameEffect.NavigateToMenuGameScreen -> {
                    navController.navigate("gameMenu")
//                    val gameJson = Gson().toJson(effect.game)
//                    navController.navigate(NavRoutes.EndGame.route + "/${gameJson}" + "/${effect.addingCoins}")
                }

//                EndGameEffect.NavigateToBack -> {
//                    navController.navigate("gameMenu") {
//                        popUpTo("gameMenu") { inclusive = true }
//                    }
//                }
            }
        }
    }


    BackHandler {
        viewModel.onEvent(EndGameEvent.NavigateToBack)
//        navController.navigate("gameMenu") {
//            popUpTo("gameMenu") { inclusive = true }
//        }
    }

    when (val state = viewState) {
        is EndGameState.Error -> {}
        is EndGameState.Loading -> {}
        is EndGameState.Ready -> {
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
                        viewModel.onEvent(EndGameEvent.ClickOnNavigateEndButton)
//                navController.navigate("gameMenu")
                    }
                ) {
                    Text(
                        text = "Home",
                        color = MaterialTheme.colorScheme.onPrimary // Цвет текста
                    )
                }
            }
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