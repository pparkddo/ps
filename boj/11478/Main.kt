import java.io.BufferedReader
import java.io.InputStreamReader

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    val s = readLine()

    val set = HashSet<String>()

    for (from in s.indices) {
        for (to in s.indices) {
            if (to < from) {
                continue
            }
            set.add(s.subSequence(from, to+1).toString())
        }
    }

    println(set.size)
}
