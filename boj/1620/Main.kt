import java.io.BufferedReader
import java.io.InputStreamReader

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    val (n, m) = readLine().split(" ").map { it.toInt() }

    val list = ArrayList<String>()
    val map = HashMap<String, Int>()

    for (index in 0 until n) {
        val name = readLine()
        map[name] = index + 1
        list.add(name)
    }

    val answer = StringBuilder()

    for (index in 0 until m) {
        val question = readLine();

        when (isNumeric(question)) {
            true -> answer.append(list[question.toInt()-1])
            false -> answer.append(map[question])
        }

        answer.append("\n")
    }

    println(answer.trim())
}

fun isNumeric(value: String) = value.toIntOrNull() != null