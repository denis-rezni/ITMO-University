import java.util.*
import kotlin.collections.ArrayList

private fun readInts() = readLine()!!.split(' ').map { it.toInt() }
fun main() {
    val (n, m) = readInts()
    val graph = ArrayList<ArrayList<Pair<Int, Int>>>()
    //graph[i] = ArrayList<Edge<secondVertice, weight>>
    for (i in 0 until n) {
        graph.add(ArrayList())
    }
    for (i in 0 until m) {
        val (v, u, w) = readInts()
        graph[v-1].add(Pair(u-1, w))
        graph[u-1].add(Pair(v-1, w))
    }
//    val parents = IntArray(n)
//    parents.fill(-1)
    val numToWeight = IntArray(n)
    val queue = TreeSet<Int>(kotlin.Comparator { o1:Int, o2:Int ->

        if(numToWeight[o1] > numToWeight[o2]){
            return@Comparator 1
        } else if(numToWeight[o1] < numToWeight[o2]) {
            return@Comparator -1
        } else {
            return@Comparator o1.compareTo(o2)
        }
    })
    val taken = BooleanArray(n)
    for (i in 0 until n) {
        numToWeight[i] = Integer.MAX_VALUE
    }
    numToWeight[0] = 0
    taken[0] = true
    for (i in 0 until n) {
        queue.add(i)
    }
    var counter: Long = 0
    while (!queue.isEmpty()) {
//        println("${queue}")
        val v = queue.pollFirst()!!
//        println("v: $v numToWeight[v]: ${numToWeight[v]!!}")
//        for (i in 0 until n){
//            print("$i: ${numToWeight[i]}; ")
//        }
//        println()
//        println("${queue}")
//        println()
//        println()
        counter += numToWeight[v]
        taken[v] = true
        for (u in graph[v]) {
            if(!taken[u.first] && numToWeight[u.first] > u.second){
                queue.remove(u.first)
//                parents[u.first] = v
                numToWeight[u.first] = u.second
                queue.add(u.first)
            }
        }
    }
//    println(Arrays.toString(parents))
    println(counter)


}