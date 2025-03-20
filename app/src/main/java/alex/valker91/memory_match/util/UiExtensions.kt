package alex.valker91.memory_match.util

fun Long.displayTime(): String {
    val m = this  % 3600 / 60
    val s = this  % 60

    return "${displaySlot(m)}:${displaySlot(s)}"
}

private fun displaySlot(count: Long): String {
    return if (count / 10L > 0) {
        "$count"
    } else {
        "0$count"
    }
}