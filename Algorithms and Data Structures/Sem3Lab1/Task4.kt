private fun readInts() = readLine()!!.split(' ').map { it.toInt() }

fun main() {
    val (n, m) = readInts()
    val graph = ArrayList<ArrayList<Int>>()
    for (i in 0 until n) {
        graph.add(ArrayList())
    }
    for (i in 0 until m) {
        val (first, second) = readInts().map { it - 1 }
        graph[first].add(second)
        graph[second].add(first)
    }

    val res = HashSet<Int>()

    fun findCutPoints() {

        var t = 0;
        val visited = BooleanArray(n)
        val timeIn = IntArray(n)
        val subTreeTime = IntArray(n)

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
                    if (subTreeTime[u] >= timeIn[v] && parent != -1) {
                        //cut point
                        res.add(v+1)
                    }
                }
            }
            if(children >= 2 && parent == -1){
                res.add(v+1)
            }

//            println("${v + 1} time: ${t - 1} subtreeTime: ${subTreeTime[v]}")
        }

        for (i in 0 until n) {
            if (!visited[i]) {
                dfsCutPoints(i, -1)
            }
        }


    }

    findCutPoints()
    println(res.size)
    res.stream().sorted().forEach { print("$it ") }


}