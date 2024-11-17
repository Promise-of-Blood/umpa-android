package com.pob.umpa.ui.theme

import androidx.compose.ui.graphics.Color

//val Purple80 = Color(0xFFD0BCFF)
//val PurpleGrey80 = Color(0xFFCCC2DC)
//val Pink80 = Color(0xFFEFB8C8)
//
//val Purple40 = Color(0xFF6650a4)
//val PurpleGrey40 = Color(0xFF625b71)
//val Pink40 = Color(0xFF7D5260)

//enum class UmpaColor(val color : Color) {
//    Main(Color(0xFF5C79AF)),
//    Secondary(Color(0xFF0E2F6B)),
//    Terri(Color(0xFF337AF7)),
//    LightBlue(Color(0xFFE5EEFF)),
//    White(Color(0xFFFFFFFF)),
//    Black(Color(0xFF000000)),
//    Grey(Color(0xFF9E9E9E)),
//    LightGray(Color(0xFFEBEBEB)),
//    // Middle Grey, naming by 김윤재
//    MiddleGrey(Color(0xFFC2C2C2))
//}

@JvmInline
value class UmpaColor private constructor(val color : Color) {
    companion object {
        val Main = Color(0xFF5C79AF)
        val Secondary = Color(0xFF0E2F6B)
        val Terri = Color(0xFF337AF7)
        val LightBlue = Color(0xFFE5EEFF)
        val White = Color(0xFFFFFFFF)
        val Black = Color(0xFF000000)
        val Grey = Color(0xFF9E9E9E)
        val LightGray = Color(0xFFEBEBEB)
        // Middle Grey, naming by 김윤재
        val MiddleGrey = Color(0xFFC2C2C2)
    }
}

