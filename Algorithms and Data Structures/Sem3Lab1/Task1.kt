private fun readInts() = readLine()!!.split(' ').map { it.toInt() }

fun main() {
    val (n, m) = readInts()
    val graphOut = ArrayList<ArrayList<Int>>()
    for (i in 0 until n) {
        graphOut.add(ArrayList())
    }
    for (i in 0 until m) {
        val (first, second) = readInts().map { it - 1 }
        graphOut[first].add(second)
    }
    if (isCyclic(graphOut)) {
        print(-1)
        return
    }
    var visited = BooleanArray(n)
    val res = ArrayList<Int>()
    for (v in 0 until n) {
        if (!visited[v]) {
            topsortDfs(graphOut, visited, v, res)
        }
    }

    res.reverse()
    res.stream().map { it + 1 }.forEach { print("$it ") }
}

private fun topsortDfs(graphOut: List<List<Int>>, visited: BooleanArray, v: Int, res: ArrayList<Int>) {
    visited[v] = true
    for (u in graphOut[v]) {
        if (!visited[u]) {
            topsortDfs(graphOut, visited, u, res)
        }
    }
    res.add(v)
}

private fun isCyclic(graphOut: List<List<Int>>): Boolean {
    val n = graphOut.size
    var color = IntArray(n)//0 - white, 1 - grey, 2 - black
    for (v in 0 until n) {
        if (color[v] != 2) {
//            println(Arrays.toString(color))
            if (hasCycleDfs(graphOut, color, v)) {
                return true
            }
        }
    }
    return false
}

private fun hasCycleDfs(graphOut: List<List<Int>>, color: IntArray, v: Int): Boolean {
    color[v] = 1
//    println(v)
    for (u in graphOut[v]) {
        if (color[u] == 1) {
            return true
        } else if (color[u] == 0) {
            if (hasCycleDfs(graphOut, color, u)) {
                return true
            }
        }
    }
    color[v] = 2
    return false
}












