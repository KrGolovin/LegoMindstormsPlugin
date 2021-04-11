package ru.krgolovin.lego.mindstorm.plugin

interface Graph {
    fun addEdge(from: Int, to :Int)

    fun getResult() : MutableList<Int>
}