import java.io.BufferedReader
import java.io.InputStreamReader

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    val (hour, minute) = readLine().split(" ").map { it.toInt() }
    val m = readLine().toInt()

    val minutes = hour * 60 + minute + m

    println("${minutes / 60 % 24} ${minutes % 60}")
}
