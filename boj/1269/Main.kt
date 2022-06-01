import java.io.BufferedReader
import java.io.InputStreamReader

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    readLine()

    val a = readLine().split(" ").map { it.toInt() }.toSet()
    val b = readLine().split(" ").map { it.toInt() }.toSet()

    println(a.minus(b).size + b.minus(a).size)
}