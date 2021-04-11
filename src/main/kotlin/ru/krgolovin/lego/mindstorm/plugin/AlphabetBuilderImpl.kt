package ru.krgolovin.lego.mindstorm.plugin

class AlphabetBuilderImpl() : AlphabetBuilder {

    override fun read() {
        val countOfString = readLine()?.toInt()
        countOfString ?: throw Exception("Count of lines missing")
        val strings = Array(countOfString) { "" }
        var maxSizeOfString = 0
        repeat(countOfString) {
            val parts = readLine()?.split('@')
            parts ?: throw Exception("Incorrect entry format $it")
            val i = parts[1].toInt()
            if (i < 0 || i >= countOfString) throw Exception("Incorrect index of string")
            strings[i] = parts[0]
            maxSizeOfString = maxOf(maxSizeOfString, parts[0].length)
        }
        repeat(maxSizeOfString) { charIndex ->
            var currChar = ' '
            repeat(countOfString) { stringIndex ->
                if (strings[stringIndex].length <= charIndex) {
                    if (currChar != ' ') {
                        isImpossible = true
                        return
                    }
                } else {
                    if (currChar != ' ') {
                        graph.addEdge(
                            currChar.toInt() - 'a'.toInt(),
                            strings[stringIndex][charIndex].toInt() - 'a'.toInt()
                        )
                    }
                    currChar = strings[stringIndex][charIndex]
                }
            }
        }
    }

    override fun getAlphabet(): List<Char>? {
        if (isImpossible) return null
        return try {
            val resultList = graph.getResult()
            resultList.map {
                (resultList[it] + 'a'.toInt()).toChar()
            }
        } catch (e: CycleException) {
            null
        }
    }

    private val graph = GraphImpl()

    private var isImpossible = false

}