package alex.valker91.memory_match.composable

import alex.valker91.memory_match.Greeting
import alex.valker91.memory_match.R
import alex.valker91.memory_match.ui.theme.Memory_MatchTheme
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun RoundedButton(
    id: Int,
    isBackDisplayed: Boolean,
    cardValue: Int,
    onClick: (Int) -> Unit // Функция, которая принимает ID кнопки
) {
    val imageResId = if (!isBackDisplayed) getImage(cardValue) else null
    Log.d("sdfsd5fddsf5s6f", "id: $id isBackDisplayed: $isBackDisplayed")
    Button(
        onClick = {
            onClick(id)
        },
        shape = RoundedCornerShape(16.dp),
        colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.primary),
        modifier = Modifier.size(65.dp),
        content = {
            if (imageResId != null) {
                Image(
                    painter = painterResource(id = imageResId),
                    contentDescription = "Card Image",
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
    )
}

private fun getImage(id: Int): Int? {
    Log.d("Kurami0895678", " getImage: $id")
    val  imageMap: Map<Int, Int> = mapOf(
        1 to R.drawable.baby_ghost,
        2 to R.drawable.cat,
        3 to R.drawable.devil,
        4 to R.drawable.happy_pumpkin,
        5 to R.drawable.heart,
        6 to R.drawable.leaf,
        7 to R.drawable.lollipop,
        8 to R.drawable.spider,
        9 to R.drawable.vampire,
        10 to R.drawable.witch
    )
    return imageMap[id]
}

@Preview(showBackground = true)
@Composable
fun RoundedButtonPreview() {
    Memory_MatchTheme {
        Column(Modifier.padding(16.dp)) {
            RoundedButton(id = 1, isBackDisplayed = false, 1, onClick = {})
        }
    }
}