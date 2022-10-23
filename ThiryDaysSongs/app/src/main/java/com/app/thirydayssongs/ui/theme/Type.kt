package com.app.thirydayssongs.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.app.thirydayssongs.R

val sirachaFont= FontFamily(
    Font(R.font.sriracha_regular)
)

// Set of Material typography styles to start with
val Typography = Typography(
    h1 = TextStyle(
        fontFamily = sirachaFont,
        fontWeight = FontWeight.Bold,
        fontSize = 25.sp
    ),
    h3 = TextStyle(
        fontFamily = sirachaFont,
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp
    ),
    body1 = TextStyle(
        fontFamily = sirachaFont,
        fontWeight = FontWeight.Normal,
        fontSize = 15.sp
    ),
    body2 = TextStyle(
        fontFamily = sirachaFont,
        fontWeight = FontWeight.Normal,
        fontSize = 13.sp
    )
)