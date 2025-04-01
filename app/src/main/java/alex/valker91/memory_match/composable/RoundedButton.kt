package alex.valker91.memory_match.composable

import alex.valker91.memory_match.Greeting
import alex.valker91.memory_match.ui.theme.Memory_MatchTheme
import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun RoundedButton(buttonId: Int) {
    Button(
        onClick = {
            Log.d("Kurami0870", "Button with ID $buttonId clicked!")
        }, // Обработка нажатия
        shape = RoundedCornerShape(16.dp), // Закругленные углы
        colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.primary),
        modifier = Modifier.size(65.dp),
        content = { }
    )
}

@Preview(showBackground = true)
@Composable
fun RoundedButtonPreview() {
    Memory_MatchTheme {
        val id = 1
        Column(Modifier.padding(16.dp)) {
            RoundedButton(id)
        }
    }
}