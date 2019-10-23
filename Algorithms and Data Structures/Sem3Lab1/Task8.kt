private fun readInts() = readLine()!!.split(' ').map { it.toInt() }
internal fun countDist(x1: Int, x2: Int, y1: Int, y2: Int): Double {
    return Math.sqrt(((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2)).toDouble())
}
fun main() {
    val n: Int = readInts().first()
    val xs = IntArray(n)
    val ys = IntArray(n)

    for (i in 0 until n) {
        val (x, y) = readInts()
        xs[i] = x
        ys[i] = y
    }

//    for (i in 0 until n) {
//        for (j in i until n) {
//            val first = points[i]
//            val second = points[j]
//            val dist = countDist(first, second)
//            graph[i][j] = dist
//            graph[j][i] = dist
//        }
//    }


    fun printGraph(graph: List<List<Double>>) {
        println()
        for (i in graph.indices) {
            println("${i}: ${graph[i]}")
        }
    }

//    printGraph(graph)

    val taken = BooleanArray(n)
    val minEdge = DoubleArray(n) { Double.MAX_VALUE }
    val ends = IntArray(n) { -1 }
    var dist = 0.0
    minEdge[0] = 0.0
    var v: Int
    for (i in 0 until n) {
        v = -1
        for (j in 0 until n) {
            if (!taken[j] && (v == -1 || minEdge[j] < minEdge[v])) {
                v = j
            }
        }
        taken[v] = true
        if (ends[v] != -1) {
            dist += countDist(xs[v], xs[ends[v]], ys[v], ys[ends[v]])
        }
        for (j in 0 until n) {
            if (countDist(xs[v], xs[j], ys[v], ys[j]) < minEdge[j]) {
                minEdge[j] = countDist(xs[v], xs[j], ys[v], ys[j])
                ends[j] = v
            }
        }
//        println(Arrays.toString(parents))
    }

//    println((1 until n).map { Pair(it, parents[it]) }.map { pair -> graph[pair.first][pair.second] }.sum())
    print(dist)

}