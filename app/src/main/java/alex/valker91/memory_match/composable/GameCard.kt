package alex.valker91.memory_match.composable

import alex.valker91.memory_match.R
import alex.valker91.memory_match.model.Game
import alex.valker91.memory_match.ui.theme.Memory_MatchTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material3.Text
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun GameCard(game: Game) {
    ElevatedCard(
        modifier = Modifier.size(250.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface,
            contentColor = MaterialTheme.colorScheme.onBackground,
        ),
        shape = MaterialTheme.shapes.medium,
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(), // Заполняем всю карточку
//            horizontalAlignment = Alignment.CenterHorizontally,
//            verticalArrangement = Arrangement.SpaceBetween // Распределяем элементы
        ) {
//             Картинка
            Image(
                imageVector = ImageVector.vectorResource(id = R.drawable.pokemon_logo), // Замените на ваш ресурс
                contentDescription = "Game Image",
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f), // Занимает свободное пространство
//                contentScale = ContentScale.Crop // Обрезаем или масштабируем изображение
            )

            // Текст
            Text(
                text = "Game ${game.gameNumber}",
                fontSize = 24.sp, // Размер шрифта
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.padding(8.dp) // Отступы вокруг текста
            )
            Text(
                text = "Coin ${game.numberOfCoins}",
                fontSize = 24.sp, // Размер шрифта
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.padding(8.dp) // Отступы вокруг текста
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GameCardPreview() {
    Memory_MatchTheme {
        val game: Game = Game(1, 123)
        Column(Modifier.padding(16.dp)) {
            GameCard(game)
        }
    }
}