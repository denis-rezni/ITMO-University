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
        if (first < second) {
            edgesArray[i] = Pair(first, second)
        } else {
            edgesArray[i] = Pair(second, first)
        }
    }

    val visited = BooleanArray(n)
    val timeIn = IntArray(n)
    val subTreeTime = IntArray(n)
    fun findCutPoints() {

        var t = 0;


        fun dfsCutPoints(v: Int, parent: Int) {
            visited[v] = true
//            println("$v time: $t")
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

        for (i in 0 until n) {
            if (!visited[i]) {
                dfsCutPoints(i, -1)
            }
        }


    }

    findCutPoints()
//    cutPoints.stream().sorted().forEach { print("$it ") }


    visited.fill(false)
    var colorsNum = -1
    val components = HashMap<Pair<Int, Int>, Int>()
    fun dfsPaintEdges(v: Int, color: Int, parent: Int) {
        visited[v] = true
        for (u in graph[v]) {
            if (u == parent) {
                continue
            }
            if (!visited[u]) {
                if (subTreeTime[u] >= timeIn[v]) {
                    colorsNum++
                    if (u < v) {
                        components[Pair(u, v)] = colorsNum
                    } else {
                        components[Pair(v, u)] = colorsNum
                    }
                    dfsPaintEdges(u, colorsNum, v)
                } else {
                    if (u < v) {
                        components[Pair(u, v)] = color
                    } else {
                        components[Pair(v, u)] = color
                    }
                    dfsPaintEdges(u, color, v)
                }
            } else if (timeIn[u] < timeIn[v]) {
                if (u < v) {
                    components[Pair(u, v)] = color
                } else {
                    components[Pair(v, u)] = color
                }
            }

        }
    }

    for (v in 0 until n) {
        if (!visited[v]) {
            dfsPaintEdges(v, ++colorsNum, -1)
        }
    }


    println(colorsNum)
    for (i in 0 until m) {
        print("${components[edgesArray[i]]} ")
    }


}