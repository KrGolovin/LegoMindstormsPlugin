package ru.krgolovin.lego.mindstorm.plugin

import java.io.File

class AlphabetBuilderImpl() : AlphabetBuilder {

    override fun read() {
        val countOfString = readLine()?.toInt()
        countOfString ?: throw Exception("Count of lines missing")
        val strings = Array(countOfString) { "" }
        var maxSizeOfString = 0
        repeat(countOfString) {
            val parts = readLine()?.split('%')
            parts ?: throw Exception("Incorrect entry format $it")
            val i = parts[1].toInt()
            if (i < 0 || i >= countOfString) throw Exception("Incorrect index of string")
            strings[i] = parts[0]
            maxSizeOfString = maxOf(maxSizeOfString, parts[0].length)
        }

        for (stringIndex in 1 until countOfString) {
            var charIndex = 0
            while ((strings[stringIndex].length > charIndex) &&
                (strings[stringIndex - 1].length > charIndex) &&
                (strings[stringIndex][charIndex] == strings[stringIndex - 1][charIndex])
            ) {
                charIndex++
            }
            if ((strings[stringIndex].length <= charIndex) &&
                (strings[stringIndex - 1].length > charIndex)
            ) {
                isImpossible = true
                return
            }
            if ((strings[stringIndex].length > charIndex) &&
                (strings[stringIndex - 1].length > charIndex)
            ) {
                graph.addEdge(
                    strings[stringIndex - 1][charIndex].toInt() - 'a'.toInt(),
                    strings[stringIndex][charIndex].toInt() - 'a'.toInt()
                )
            }
        }
    }

    override fun getAlphabet(): List<Char>? {
        if (isImpossible) return null
        return try {
            val resultList = graph.getResult()
            if (resultList.size != sizeOfAlphabet) {
                return null
            }
            resultList.map {
                (it + 'a'.toInt()).toChar()
            }
        } catch (e: CycleException) {
            null
        }
    }

    private val graph = GraphImpl()

    private var isImpossible = false

    private val sizeOfAlphabet = 26

}