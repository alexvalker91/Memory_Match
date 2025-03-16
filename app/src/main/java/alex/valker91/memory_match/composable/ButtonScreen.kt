package alex.valker91.memory_match.composable

import alex.valker91.memory_match.Greeting
import alex.valker91.memory_match.ui.theme.Memory_MatchTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ButtonScreen() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp), // Отступы от края экрана
        verticalArrangement = Arrangement.spacedBy(14.dp) // Отступы между строками
    ) {
        repeat(5) { rowIndex -> // 5 строк
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly // Отступы между кнопками
            ) {
                repeat(4) { columnIndex -> // 4 кнопки в каждой строке
                    RoundedButton()
                }
            }
        }
    }
}

@ScreenPreview
@Composable
private fun ButtonScreenPreview() {
    Memory_MatchTheme {
        ButtonScreen()
    }
}