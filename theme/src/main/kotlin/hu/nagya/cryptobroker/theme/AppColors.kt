package hu.nagya.cryptobroker.theme

import androidx.compose.ui.graphics.Color

object AppColors {
    val primary: Primary = Primary()
    val state: State = State()
    val text: Text = Text()

    data class Primary internal constructor(
        val primary0: Color = Color(0xFFFFFFFF),
        val primary90: Color = Color(0xFF0028FF),
        val primary100: Color = Color(0xFF0A28EB),
        val primary100alpha20: Color = primary100.copy(alpha = 0.2f),
        val primary100alpha30: Color = primary100.copy(alpha = 0.3f)
    )

    data class State internal constructor(
        val success: Color = Color(0xFF1FCC8F),
        val error: Color = Color(0xFFE54560),
    )

    data class Text internal constructor(
        val default: Color = Color(0xFF292E33)
    )

    data class Icon internal constructor(
        val default: Color = Color(0xFF292E33)
    )
}
