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
        val min = Math.min(first, second)
        val max = Math.max(first, second)
        map[Pair(min, max)] = if (map[Pair(min, max)] != null) (map[Pair(min, max)]!! + 1) else 1
        graph[first].add(second)
        if(first != second){
            graph[second].add(first)
        }
    }

    val res = ArrayList<Int>()
    val timeIn = IntArray(n)
    val subTreeTime = IntArray(n)

    fun findBridges() {

        var t = 0;
        val visited = BooleanArray(n)

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
    var colorsNum = 0
    val colors = IntArray(n)
    fun dfsPaint(v: Int, color: Int){
        colors[v] = color
        for(u in graph[v]){
            if(colors[u] == 0){
                if(subTreeTime[u] > timeIn[v] && map[Pair(Math.min(v, u), Math.max(v, u))] == 1){
                    colorsNum++
                    dfsPaint(u, colorsNum)
                } else {
                    dfsPaint(u, color)
                }
            }
        }
    }
    for(i in 0 until n){
        if(colors[i] == 0){
            dfsPaint(i, ++colorsNum)
        }
    }
    println(colorsNum)
    colors.forEach { print("$it ") }


}

