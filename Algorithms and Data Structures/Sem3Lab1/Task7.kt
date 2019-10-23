import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

private fun readInts() = readLine()!!.split(' ').map { it.toInt() }
private fun readNames() = readLine()!!.split(" => ")

fun main() {
    val (n, m) = readInts()
    val graphOut = ArrayList<ArrayList<Int>>()
    val graphIn = ArrayList<ArrayList<Int>>()
    val nameToNum = HashMap<String, Int>()
    val numToName = Array(n) { "" }
    for (i in 0 until n) {
        val name = readLine()!!
        nameToNum[name] = i
        numToName[i] = name
    }
    (0..2 * n).forEach { graphIn.add(ArrayList());graphOut.add(ArrayList()) }

    //i -> 2 * i (itself), 2 * i + 1(negative)
    fun isNegatve(name: String): Int {
        return (if (name.startsWith('-')) 1 else 0)
    }

    fun getNegative(v: Int): Int {
        if (v % 2 == 0) {
            return v + 1
        } else {
            return v - 1
        }
    }

    for (i in 0 until m) {
        val (firstName, secondName) = readNames()
        val first = nameToNum[firstName.substring(1)]!! * 2 + isNegatve(firstName)
        val second = nameToNum[secondName.substring(1)]!! * 2 + isNegatve(secondName)
        //first -> second
        //!second -> !first
        graphOut[first].add(second)
        graphIn[second].add(first)
        graphOut[getNegative(second)].add(getNegative(first))
        graphIn[getNegative(first)].add(getNegative(second))
    }

    fun printGraph(graph: List<List<Int>>) {
        println()
        for (i in graph.indices) {
            println("${i}: ${graph[i]}")
        }
    }

//    printGraph(graphIn)
//    printGraph(graphOut)

    val timeOut = ArrayList<Int>()
    val visited = BooleanArray(2 * n)
    fun dfsTimes(v: Int) {
        visited[v] = true
        for (u in graphOut[v]) {
            if (!visited[u]) {
                dfsTimes(u)
            }
        }
        timeOut.add(v)
    }

    for (v in 0 until 2 * n) {
        if (!visited[v]) {
            dfsTimes(v)
        }
    }


    val colors = IntArray(2 * n)
    var colorsNum = -1
    val graphOutCond = ArrayList<ArrayList<Int>>()
    val graphInCond = ArrayList<ArrayList<Int>>()

    visited.fill(false)
    fun dfsReverse(v: Int, color: Int) {
        visited[v] = true
        colors[v] = color
//        println("went to ${v + 1}, color is $color")
        for (u in graphIn[v]) {
            if (!visited[u]) {
                dfsReverse(u, color)
            } else if (color != colors[u]) {
                //to other component
                graphInCond[colors[v]].add(colors[u])
                graphOutCond[colors[u]].add(colors[v])
            }
        }
    }


    for (i in 2 * n - 1 downTo 0) {
        val curVertice = timeOut[i]
        if (!visited[curVertice]) {
            graphInCond.add(ArrayList())
            graphOutCond.add(ArrayList())
            colorsNum++
            dfsReverse(curVertice, colorsNum)
        }
    }
    colorsNum++

    for (i in 0 until n) {
        if (colors[2 * i] == colors[2 * i + 1]) {
            print(-1)
            return
        }
    }

//    for (i in 0 until n) {
//        println("${numToName[i]} in ${colors[2 * i]}")
//        println("!${numToName[i]} in ${colors[2 * i + 1]}")
//    }

    val negativeToComp = IntArray(colorsNum)
    for (i in 0 until n) {
        val first = colors[2 * i]
        //first's component
        val second = colors[2 * i + 1]
        //second's component
        //these two are negative to each other
        negativeToComp[first] = second
        negativeToComp[second] = first
    }

//    printGraph(graphInCond)
//    printGraph(graphOutCond)

    val finalColors = IntArray(colorsNum)
    finalColors.fill(2)

    //2 - hasn't visited yet
    //0 - false
    //1 - true
    for (i in 0 until colorsNum) {
        if (graphInCond[i].isEmpty()) {
            //source
            finalColors[i] = 0
            finalColors[negativeToComp[i]] = 1
        }
    }
    for (i in 0 until colorsNum) {
        if (graphOutCond[i].isEmpty()) {
            //sink
            finalColors[i] = 1
            finalColors[negativeToComp[i]] = 0
        }
    }

//    println("finalColors")
//    println(Arrays.toString(finalColors))



    fun topSortDfs(graphOut: List<List<Int>>, visited: BooleanArray, v: Int, topSort: ArrayList<Int>) {
        visited[v] = true
        for (u in graphOut[v]) {
            if (!visited[u]) {
                topSortDfs(graphOut, visited, u, topSort)
            }
        }
        topSort.add(v)
    }

    visited.fill(false)
    val topSort = ArrayList<Int>()
    for (v in 0 until colorsNum) {
        if (!visited[v]) {
            topSortDfs(graphOutCond, visited, v, topSort)
        }
    }

    topSort.reverse()
//    println(topSort.toString())
    for (i in 0 until colorsNum) {
        val v = topSort[i]
        if (finalColors[v] == 2) {
            if ((0 until graphInCond[v].size).map { graphInCond[v][it] }.map { finalColors[it] }.contains(1)) {
                finalColors[v] = 1
                finalColors[negativeToComp[v]] = 0
            } else {
                finalColors[v] = 0
                finalColors[negativeToComp[v]] = 1
            }
        }
    }
//    println(Arrays.toString(finalColors))

    val res = ArrayList<String>()
    for (i in 0 until 2 * n) {
        val willCome = finalColors[colors[i]] == 1
        if (i % 2 == 0 && willCome) {
            res.add(numToName[i / 2])
        }
    }
    println(res.size)
    res.forEach { println(it) }

}