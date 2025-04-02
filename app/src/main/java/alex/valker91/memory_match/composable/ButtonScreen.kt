package alex.valker91.memory_match.composable

import alex.valker91.memory_match.features.game.model.MemoryCard
import alex.valker91.memory_match.ui.theme.Memory_MatchTheme
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ButtonScreen(
    cards: Array<MemoryCard>, onCardClick: (Int) -> Unit
) {
    var currentId = 0 // Начальный ID

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


                    Log.d("sdfsd5fds6f", "rowIndex: $rowIndex + columnIndex: $columnIndex ")
//                    val currentId = rowIndex  * 4 + columnIndex  // Уникальный ID кнопки
                    Log.d("sdfsd5fds6f", "currentId: $currentId")
                    val cardState = cards[currentId] // Получаем состояние карты по ID


                    RoundedButton(
                        id = currentId,
                        isBackDisplayed = cardState.isBackDisplayed,
                        cardValue = cardState.value,
                        onClick = onCardClick // Передаём обработчик клика
                        )
                    currentId++
                }
            }
        }
    }
}

@ScreenPreview
@Composable
private fun ButtonScreenPreview() {
    Memory_MatchTheme {
        ButtonScreen(cards = emptyArray(), onCardClick = {})
    }
}