package alex.valker91.memory_match.composable

import alex.valker91.memory_match.ui.theme.Memory_MatchTheme
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.material3.Text
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp

@Composable
fun PlayButton() {
    Button(
        onClick = { /* Обработка нажатия */ },
        modifier = Modifier.defaultMinSize(minWidth = 120.dp),
        shape = CircleShape, // Полностью скругленная форма
        border = BorderStroke(width = 2.dp, color = MaterialTheme.colorScheme.outline), // Граница другого цвета (например, синяя)
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.onPrimary
        )
    ) {
        Text(
            text = "Play",
            color = MaterialTheme.colorScheme.onPrimary // Цвет текста
        )
    }
}

@Composable
fun TextView(text: String) {
    Text(
        text = text, // Текст внутри
        fontSize = 16.sp, // Размер шрифта
        color = MaterialTheme.colorScheme.onPrimary, // Цвет текста
        textAlign = TextAlign.Center, // Выравнивание текста по центру
        modifier = Modifier
            .defaultMinSize(minWidth = 120.dp)
            .background(
                color = MaterialTheme.colorScheme.primary, // Цвет фона внутри границы
                shape = CircleShape
            )
            .border(
                width = 2.dp, // Толщина границы
                color = MaterialTheme.colorScheme.outline, // Цвет границы
                shape = CircleShape // Полностью скругленная форма
            )
    )
}

@Preview(showBackground = true)
@Composable
fun PlayButtonPreview() {
    Memory_MatchTheme {
        Column(Modifier.padding(16.dp)) {
            PlayButton()
            TextView("SomeText")
        }
    }
}