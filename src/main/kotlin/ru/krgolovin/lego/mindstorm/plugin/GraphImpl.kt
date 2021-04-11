package ru.krgolovin.lego.mindstorm.plugin

class GraphImpl() : Graph {

    override fun addEdge(from: Int, to: Int) {
        nodeArray[from].setOfEdge.add(to)
        nodeArray[to].isStart = false
        nodeArray[to].inPower += 1
    }

    override fun getResult(): MutableList<Int> {
        val resultList = mutableListOf<Int>()
        val isVisited = Array(size) { false }
        val inPowerValues = Array(size) {
            nodeArray[it].inPower
        }
        for (i in 0 until size) {
            if (nodeArray[i].isStart) {
                dfs(i, resultList, isVisited, inPowerValues)
            }
        }
        return resultList
    }

    private fun dfs(
        from: Int,
        resultList: MutableList<Int>,
        isVisited: Array<Boolean>,
        inPowerValues: Array<Int>
    ) {
        resultList.add(from)
        isVisited[from] = true
        for (to in nodeArray[from].setOfEdge) {
            if (isVisited[to]) throw CycleException()
            if (inPowerValues[to] > 1) {
                inPowerValues[to]--
            } else {
                dfs(to, resultList, isVisited, inPowerValues)
            }
        }
    }

    private val nodeArray: Array<Node> = Array(size) {
        Node(true, 0, mutableSetOf())
    }

    private data class Node(
        var isStart: Boolean,
        var inPower: Int,
        val setOfEdge: MutableSet<Int>
    )

    companion object {
        const val size = 26
    }

}