import java.io.BufferedReader
import java.io.InputStreamReader

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    val dices = readLine().split(" ").map { it.toInt() }.sorted()

    val counter = dices.groupingBy { it }.eachCount()

    val result = when (dices.size - counter.size + 1) {
        3 -> 10000 + counter.firstNotNullOf { it.key } * 1000
        2 -> 1000 + counter.maxByOrNull{ it.value }!!.key * 100
        else -> dices.last() * 100
    }

    println(result)
}
