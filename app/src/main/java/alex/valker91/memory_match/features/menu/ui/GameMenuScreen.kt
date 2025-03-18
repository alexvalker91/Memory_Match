package alex.valker91.memory_match.features.menu.ui

import alex.valker91.memory_match.composable.GameCard
import alex.valker91.memory_match.composable.PlayButton
import alex.valker91.memory_match.composable.ScreenPreview
import alex.valker91.memory_match.features.game.vm.GameViewModel
import alex.valker91.memory_match.features.menu.model.MainEvent
import alex.valker91.memory_match.features.menu.model.MenuEffect
import alex.valker91.memory_match.features.menu.model.MenuState
import alex.valker91.memory_match.features.menu.vm.MenuViewModel
import alex.valker91.memory_match.model.Game
import alex.valker91.memory_match.navigation.NavRoutes
import alex.valker91.memory_match.ui.theme.Memory_MatchTheme
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.google.gson.Gson
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun GameMenuScreen(
    navController: NavController,
    viewModel: MenuViewModel = hiltViewModel()
) {
    Log.d("Logdfgdfgdfs1gCa1t", "INIT SCREEN")
    val viewState by viewModel.state.collectAsStateWithLifecycle()
//    val effect by viewModel.effect.collectAsStateWithLifecycle()

    LaunchedEffect(viewState){
        Log.d("Logdfgdfgdfs1gCa1t", "OperatorDepositScreen STATE: $viewState")
    }
//    LaunchedEffect(effect){
//        Log.d("LogCat", "OperatorDepositScreen effect: $effect")
//    }

    LaunchedEffect(Unit) {
        viewModel.effect.collect { effect ->
            when (effect) {
//                is MenuEffect.ShowToast -> {
//                    Toast.makeText(context, effect.message, Toast.LENGTH_SHORT).show()
//                }
//                is MenuEffect.NavigateToGame -> {
//                    navController.navigate("game/${effect.game.gameNumber}")
//                }

                is MenuEffect.NavigateToGameScreen -> {
//                    effect.game
                    val gameJson = Gson().toJson(effect.game)
                    navController.navigate(NavRoutes.Game.route + "/${gameJson}")
//                    navController.navigate(NavRoutes.Game.route + "/${gameJson}")
                }
                MenuEffect.ShowToast -> {}
            }
        }
    }

    when (val state = viewState) {
        is MenuState.Error -> {}
        MenuState.Loading -> {}
        is MenuState.Ready -> {
            val game: Game = Game(state.gameLevel, state.countOfCoins)
            Column(
                modifier = Modifier.fillMaxSize().padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                GameCard(game)
                Spacer(modifier = Modifier.height(16.dp))
                PlayButton()
                Button(
                    onClick = {
//                        val game = Game(3, 33)
//                        val gameJson = Gson().toJson(game)
//                        navController.navigate(NavRoutes.Game.route + "/${gameJson}")
                        viewModel.onEvent(MainEvent.ClickOnPlayButton)
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
private fun MenuScreenPreview() {
    Memory_MatchTheme {
        val mockNavController = rememberNavController()
        GameMenuScreen(mockNavController)
    }
}