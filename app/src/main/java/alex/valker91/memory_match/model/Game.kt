package alex.valker91.memory_match.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Game(
    val gameNumber: Int,
    val numberOfCoins: Int
) : Parcelable
