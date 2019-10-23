private fun readInts() = readLine()!!.split(' ').map { it.toInt() }


fun main() {
    val (n, m) = readInts()
    val graphOut = ArrayList<ArrayList<Int>>()
    val graphIn = ArrayList<ArrayList<Int>>()

    for (i in 0 until n) {
        graphOut.add(ArrayList())
        graphIn.add(ArrayList())
    }
    for (i in 0 until m) {
        val (first, second) = readInts().map { it - 1 }
        graphOut[first].add(second)
        graphIn[second].add(first)
    }
    val timeOut = ArrayList<Int>()
    val visited = BooleanArray(n)
    fun dfsTimes(v: Int) {
        visited[v] = true
        for (u in graphOut[v]) {
            if (!visited[u]) {
                dfsTimes(u)
            }
        }
        timeOut.add(v)
    }

    for (v in 0 until n) {
        if (!visited[v]) {
            dfsTimes(v)
        }
    }


    val colors = IntArray(n)
    var colorsNum = 0
    val visitedComponents = HashSet<Int>()
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
                visitedComponents.add(colors[u])
            }
        }
    }


    var res = 0
    for (i in n - 1 downTo 0) {
        val curVertice = timeOut[i]
        if (!visited[curVertice]) {
            colorsNum++
            visitedComponents.clear()
            dfsReverse(curVertice, colorsNum)
            res += visitedComponents.size
        }
    }
    print(res)


}