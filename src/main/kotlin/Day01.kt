data class FoodItem(var calories: Int)
data class Elf(var food: List<FoodItem>)

private fun Elf.totalCalories() = food.sumOf { it.calories }

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
        food = stringList.map { stringValue ->
            FoodItem(stringValue.toInt())
        }
    )
}

fun main() {
    fun part1(input: List<String>): Int = input.toElves().maxOf {
            it.totalCalories()
        }

    fun part2(input: List<String>): Int = input.toElves().sortedByDescending { elf ->
            elf.totalCalories()
        }.take(3).sumOf {
            it.totalCalories()
        }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day01_test")
    check(part1(testInput) == 24000)
    check(part2(testInput) == 45000)

    val input = readInput("Day01")
    println(part1(input))
    println(part2(input))
}
