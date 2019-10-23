private fun readInts() = readLine()!!.split(' ').map { it.toInt() }

fun main() {
    val (n, m) = readInts()
    val graph = ArrayList<ArrayList<Int>>()
    val map = HashMap<Pair<Int, Int>, Int>()
    for (i in 0 until n) {
        graph.add(ArrayList())
    }
    for (i in 0 until m) {
        val (first, second) = readInts().map { it - 1 }
        map[Pair(first, second)] = i
        map[Pair(second, first)] = i
        graph[first].add(second)
        graph[second].add(first)
    }

    val res = ArrayList<Int>()

    fun findBridges() {

        var t = 0;
        val visited = BooleanArray(n)
        val timeIn = IntArray(n)
        val subTreeTime = IntArray(n)

        fun dfsBridges(v: Int, parent: Int) {
            visited[v] = true
//            println("$v time: $t")
            timeIn[v] = t
            subTreeTime[v] = t++
            for (u in graph[v]) {
                if (u == parent) {
                    continue
                }
                if (visited[u]) {
                    //back edge
                    subTreeTime[v] = Math.min(subTreeTime[v], timeIn[u])
                } else {
                    //tree edge
                    dfsBridges(u, v)
                    subTreeTime[v] = Math.min(subTreeTime[v], subTreeTime[u])
                    if (subTreeTime[u] > timeIn[v]) {
                        //bridge
                        res.add(map[Pair(v, u)]!!)
                    }
                }
            }

//            println("${v + 1} time: ${t - 1} subtreeTime: ${subTreeTime[v]}")
        }

        for (i in 0 until n) {
            if (!visited[i]) {
                dfsBridges(i, -1)
            }
        }


    }

    findBridges()
    println(res.size)
    res.stream().sorted().map { it + 1 }.forEach { print("$it ") }


}