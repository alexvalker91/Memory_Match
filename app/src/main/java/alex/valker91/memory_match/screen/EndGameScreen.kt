package alex.valker91.memory_match.screen

import alex.valker91.memory_match.composable.ScreenPreview
import alex.valker91.memory_match.ui.theme.Memory_MatchTheme
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

@Composable
fun EndGameScreen() {
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
            onClick = { /* Обработка нажатия */ }
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
        EndGameScreen()
    }
}