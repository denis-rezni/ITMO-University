import java.io.File
import java.io.PrintWriter
import java.util.*

fun main() {
    val scanner = Scanner(File("avia.in"))
    //CHANGE 10 TO 80
    //CHANGE OUTPRINT
//    val scanner = Scanner(System.`in`)
    val writer = PrintWriter(File("avia.out"))
    val n = scanner.nextInt()
    val graph: Array<IntArray> = Array(n) { IntArray(n) }
    var max = Integer.MIN_VALUE
    for (i in 0 until n){
        for (j in 0 until n){
            graph[i][j] = scanner.nextInt()
            if(max < graph[i][j]){
                max = graph[i][j]
            }
        }
    }
//    println("max: $max")

    fun checkConnectivity(bound: Int) : Boolean{
        var counter = 0
        val visited = BooleanArray(n)
        fun dfs(v: Int){
            visited[v] = true
            counter++
            for(u in 0 until n){
                if(!visited[u] && graph[v][u] <= bound){
                    dfs(u)
                }
            }
        }
        dfs(0)
        if(counter != n){
            return false
        }
        visited.fill(false)
        counter = 0
        fun dfsReverse(v: Int) {
            visited[v] = true
            counter++
            for(u in 0 until n){
//                println("u: $u v: $v cost:${graph[u][v]} bound: $bound")
//                println(Arrays.toString(visited))
                if(!visited[u] && graph[u][v] <= bound){
//                    println("going from $v to $u")
                    dfsReverse(u)
                }
            }
        }
        dfsReverse(0)
//        println("bound: $bound ${Arrays.toString(visited)}")
        return counter == n

    }

    var low = -1
    var high = max + 10
    for (step in 0 until 80){
        val m = (low + high) / 2
        if(checkConnectivity(m)){
            high = m
        } else {
            low = m
        }
    }
    writer.println(high)
//    println(low)
//    println(checkConnectivity(low))

    writer.close()
    scanner.close()
}
