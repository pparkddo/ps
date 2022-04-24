import java.io.BufferedReader
import java.io.InputStreamReader

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    val diff = 543
    println("${readLine().toInt()-diff}")
}
