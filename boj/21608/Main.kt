import java.io.BufferedReader
import java.io.InputStreamReader
import kotlin.math.pow

val dr = listOf(-1, 1, 0, 0)
val dc = listOf(0, 0, -1, 1)
const val EMPTY = -1

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    val n = readLine().toInt()

    val persons = mutableListOf<Int>()
    val favoritesByPerson = mutableMapOf<Int, Set<Int>>()
    for (index in 0 until n * n) {
        val input = readLine().split(" ").map { it.toInt() }
        val person = input[0]
        val favorites = input.slice(1..input.lastIndex).toSet()
        persons.add(person)
        favoritesByPerson[person] = favorites
    }

    val map = Array(n) { IntArray(n) { EMPTY } }

    for (person in persons) {
        var bestPosition: Position? = null

        for (row in 0 until n) {
            for (column in 0 until n) {
                if (map[row][column] != EMPTY) {
                    continue
                }

                val favorites: Set<Int> = favoritesByPerson[person]!!

                var emptyCount = 0
                var favoritesCount = 0
                for(direction in dr.indices) {
                    val nextRow = row + dr[direction]
                    val nextColumn = column + dc[direction]
                    if (!isValid(nextRow, nextColumn, map)) {
                        continue
                    }
                    if (map[nextRow][nextColumn] == EMPTY) {
                        emptyCount++
                        continue
                    }
                    if (map[nextRow][nextColumn] in favorites) {
                        favoritesCount++
                    }
                }

                val currentPosition = Position(row, column, favoritesCount, emptyCount)

                if (bestPosition == null) {
                    bestPosition = currentPosition
                    continue
                }

                if (currentPosition.isPreferThan(bestPosition)) {
                    bestPosition = currentPosition
                }
            }
        }

        map[bestPosition!!.row][bestPosition.column] = person
    }

    var result = 0

    for (row in 0 until n) {
        for (column in 0 until n) {
            val person = map[row][column]
            val favorites: Set<Int> = favoritesByPerson[person]!!

            var favoritesCount = 0
            for (direction in dr.indices) {
                val nextRow = row + dr[direction]
                val nextColumn = column + dc[direction]
                if (!isValid(nextRow, nextColumn, map)) {
                    continue
                }
                if (map[nextRow][nextColumn] in favorites) {
                    favoritesCount++
                }
            }

            result +=  10.0.pow(favoritesCount-1).toInt()
        }
    }

    println(result)
}

fun isValid(row: Int, column: Int, map: Array<IntArray>): Boolean {
    return row >= 0 && row < map.size && column >= 0 && column < map[0].size
}

data class Position(
    val row: Int,
    val column: Int,
    val favoritesCount: Int = 0,
    val emptyCount: Int = 0,
) {
    fun isPreferThan(other: Position): Boolean {
        if (favoritesCount > other.favoritesCount) {
            return true
        }
        if (favoritesCount < other.favoritesCount) {
            return false;
        }

        if (emptyCount > other.emptyCount) {
            return true
        }
        if (emptyCount < other.emptyCount) {
            return false;
        }

        if (row < other.row) {
            return true
        }
        if (row > other.row) {
            return false;
        }

        return column < other.column
    }
}