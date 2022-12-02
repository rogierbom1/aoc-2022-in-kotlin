data class Snack(var calories: Int)
data class Elf(var snacks: List<Snack>)

private fun Elf.totalCalories() = snacks.sumOf { it.calories }

private fun List<String>.toElves(): List<Elf> = this.flatMapIndexed { index, calories ->
    when {
        index == 0 || index == lastIndex -> listOf(index)
        calories.isEmpty() -> listOf(index - 1, index + 1)
        else -> emptyList()
    }
}.windowed(2, 2) { (from, to) ->
    this.slice(from..to)
}.map { stringList ->
    Elf(
        snacks = stringList.map { stringValue ->
            Snack(stringValue.toInt())
        }
    )
}

fun main() {
    fun part1(input: List<String>): Int = input.toElves().maxOf { elf ->
            elf.totalCalories()
        }

    fun part2(input: List<String>): Int = input.toElves().sortedByDescending { elf ->
            elf.totalCalories()
        }.take(3).sumOf { elf ->
            elf.totalCalories()
        }

    val testInput = readInput("Day01_test")
    check(part1(testInput) == 24000)
    check(part2(testInput) == 45000)

    val input = readInput("Day01")
    println(part1(input))
    println(part2(input))
}
