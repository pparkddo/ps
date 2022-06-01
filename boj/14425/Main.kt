import java.io.BufferedReader
import java.io.InputStreamReader

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    val (n, m) = readLine().split(" ").map { it.toInt() }

    val set = HashSet<String>();
    for (index in 0 until n) {
        set.add(readLine());
    }

    var answer = 0;

    for (index in 0 until m) {
        if (readLine() in set) {
            answer += 1;
        }
    }

    println(answer)
}