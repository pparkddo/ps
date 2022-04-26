import java.io.BufferedReader
import java.io.InputStreamReader

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    val (n, k) = readLine().split(" ").map { it.toInt() }
    val numbers = readLine().split(" ").map { it.toInt() }

    val dp = IntArray(n)
    dp[0] = numbers.first()
    for (index in 1 until numbers.size) {
        dp[index] = numbers[index] + dp[index-1]
    }

    val sum = IntArray(n-k+1)
    sum[0] = dp[k-1]
    for (index in 1 until sum.size) {
        sum[index] = dp[index+k-1] - dp[index-1]
    }

    println(sum.maxOrNull())
}