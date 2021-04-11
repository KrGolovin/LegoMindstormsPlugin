package ru.krgolovin.lego.mindstorm.plugin

fun main(args: Array<String>) {
    val alphabetBuilder = AlphabetBuilderImpl()
    alphabetBuilder.read()
    val alphabet = alphabetBuilder.getAlphabet()
    if (alphabet == null) {
        println("impossible")
    } else {
        println(alphabet.joinToString(separator = " "))
    }
}