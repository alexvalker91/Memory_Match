package alex.valker91.memory_match

import alex.valker91.memory_match.model.Game
import alex.valker91.memory_match.navigation.NavRoutes
import alex.valker91.memory_match.features.end.ui.EndGameScreen
import alex.valker91.memory_match.features.menu.ui.GameMenuScreen
import alex.valker91.memory_match.features.game.ui.GameScreen
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import alex.valker91.memory_match.ui.theme.Memory_MatchTheme
import androidx.compose.foundation.background
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Memory_MatchTheme {
                val navController = rememberNavController()
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(MaterialTheme.colorScheme.background))
                {
                    NavHost(navController, startDestination = NavRoutes.GameMenu.route) {
                        composable(NavRoutes.GameMenu.route) { GameMenuScreen(navController) }
                        composable(NavRoutes.Game.route + "/{gameJson}",
                            arguments = listOf(
                                navArgument("gameJson") { type = NavType.StringType }
                            )
                        ) { backStackEntry ->
                            val gameJson = backStackEntry.arguments?.getString("gameJson")
                            val game = Gson().fromJson(gameJson, Game::class.java)
                            GameScreen(navController, game)
                        }
                        composable(NavRoutes.EndGame.route + "/{gameJson}" + "/{numberOfAddingCoins}",
                            arguments = listOf(
                                navArgument("gameJson") { type = NavType.StringType },
                                navArgument("numberOfAddingCoins") { type = NavType.IntType }
                            )
                        ) { backStackEntry ->
                            val gameJson = backStackEntry.arguments?.getString("gameJson")
                            val game = Gson().fromJson(gameJson, Game::class.java)
                            val numberOfAddingCoins = backStackEntry.arguments?.getInt("numberOfAddingCoins") ?: 0
                            EndGameScreen(navController, game, numberOfAddingCoins)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Memory_MatchTheme {
        Greeting("Android")
    }
}