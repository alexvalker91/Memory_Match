package alex.valker91.memory_match.screen

import alex.valker91.memory_match.composable.ButtonScreen
import alex.valker91.memory_match.composable.GameCard
import alex.valker91.memory_match.composable.PlayButton
import alex.valker91.memory_match.composable.ScreenPreview
import alex.valker91.memory_match.ui.theme.Memory_MatchTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun MenuScreen() {
    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        GameCard()
        Spacer(modifier = Modifier.height(16.dp))
        PlayButton()
    }
}

@ScreenPreview
@Composable
private fun MenuScreenPreview() {
    Memory_MatchTheme {
        MenuScreen()
    }
}