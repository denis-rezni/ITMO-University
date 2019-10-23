import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

private fun readInts() = readLine()!!.split(' ').map { it.toInt() }


fun main() {
    val (n, m) = readInts()
    val graph = ArrayList<ArrayList<Int>>()
    val edgesArray = Array(m) { i -> Pair(0, 0) };
    for (i in 0 until n) {
        graph.add(ArrayList())
    }
    for (i in 0 until m) {
        val (first, second) = readInts().map { it - 1 }
        graph[first].add(second)
        graph[second].add(first)
        edgesArray[i] = Pair(Math.min(first, second), Math.max(first, second))
    }

    val visited = BooleanArray(n)
    val timeIn = IntArray(n)
    val subTreeTime = IntArray(n)
    var t = 0
    fun dfsCutPoints(v: Int, parent: Int) {
        visited[v] = true
        timeIn[v] = t
        subTreeTime[v] = t++
        var children = 0
        for (u in graph[v]) {
            if (u == parent) {
                continue
            }
            if (visited[u]) {
                //back edge
                subTreeTime[v] = Math.min(subTreeTime[v], timeIn[u])
            } else {
                //tree edge
                dfsCutPoints(u, v)
                children++
                subTreeTime[v] = Math.min(subTreeTime[v], subTreeTime[u])
            }
        }

    }

    for (v in 0 until n) {
        if (!visited[v]) {
            dfsCutPoints(v, -1)
        }
    }



    var colorsNum = -1
    val components = HashMap<Pair<Int, Int>, Int>()
    val stack = ArrayDeque<Pair<Int, Int>>()
    visited.fill(false)
    fun dfsPaint(v: Int, parent: Int) {
        visited[v] = true
        for (u in graph[v]) {
            if (u == parent) {
                continue
            }
            val pair = Pair(Math.min(v, u), Math.max(v, u))
//            println("${pair.first + 1} ${pair.second + 1}")
            if (!visited[u]) {
                stack.push(pair)
                dfsPaint(u, v)
                if (subTreeTime[u] >= timeIn[v]) {
//                    println("new color")
//                    println("pair: ${pair.first + 1} ${pair.second + 1}")
//                    println("stack: ${stack.map { Pair(it.first + 1, it.second + 1) }}")
                    colorsNum++
                    while (stack.first != pair) {
                        components[stack.first] = colorsNum
                        stack.pop()
                    }
                    components[pair] = colorsNum
                    stack.pop()
                }
            } else if (timeIn[u] < timeIn[v]) {
                stack.push(pair)
            }
        }
    }

    for (v in 0 until n) {
        if (!visited[v]) {
            colorsNum++
            dfsPaint(v, -1)
        }
    }



    println(colorsNum)
    for (i in 0 until m) {
        print("${components[edgesArray[i]]} ")
    }


}