package alex.valker91.memory_match.navigation

sealed class NavRoutes(val route: String) {
    data object GameMenu : NavRoutes("gameMenu")
    data object Game : NavRoutes("game")
    data object EndGame : NavRoutes("endGame")
}