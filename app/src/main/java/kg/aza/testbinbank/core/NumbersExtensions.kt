package kg.aza.testbinbank.core

fun Int?.orEmpty(): Int{
    return this ?: -1
}

fun Long?.orEmpty(): Long{
    return this ?: -1L
}

fun Float?.orEmpty(): Float{
    return this ?: -1F
}

fun Double?.orEmpty(): Double{
    return this ?: -1.0
}

fun Double?.orZero(): Double{
    return this?: 0.0
}