package ru.krgolovin.lego.mindstorm.plugin

interface AlphabetBuilder {
    fun read()

    fun getAlphabet() : List<Char>?
}