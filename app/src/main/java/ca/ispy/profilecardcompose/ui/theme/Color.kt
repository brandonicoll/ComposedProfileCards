package ca.ispy.profilecardcompose.ui.theme

import androidx.compose.material.Colors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val Purple200 = Color(0xFFBB86FC)
val Purple500 = Color(0xFF6200EE)
val Purple700 = Color(0xFF3700B3)
val Teal200 = Color(0xFF03DAC5)

val verLightGray = Color(0x60DCDCDC)
val lightGreen200 = Color(0x9932CD32)

@get:Composable //creates an extension function to add to material theme colors
val Colors.lightGreen: Color
    get() = lightGreen200