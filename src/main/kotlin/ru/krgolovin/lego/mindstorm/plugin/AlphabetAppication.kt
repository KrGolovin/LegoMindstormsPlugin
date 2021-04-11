package ru.krgolovin.lego.mindstorm.plugin

fun main() {
    val alphabetBuilder = AlphabetBuilderImpl()
    alphabetBuilder.read()
    val alphabet = alphabetBuilder.getAlphabet()
    if (alphabet == null) {
        println("Impossible")
    } else {
        println(alphabet.joinToString(separator = " "))
    }
}