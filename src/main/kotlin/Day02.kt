package main.kotlin

import readInput

fun String.toShape() = when (this) {
    "A", "X" -> Rock
    "B", "Y" -> Paper
    "C", "Z" -> Scissors
    else -> throw ShapeException.InvalidShapeException
}

fun String.toStrategicShape(opponentShape: Shape) = when (this) {
    "X" -> opponentShape.trumps()
    "Y" -> opponentShape
    "Z" -> opponentShape.losesTo()
    else -> throw ShapeException.InvalidShapeException
}

fun draw(player1: Shape, player2: Shape): Int = when(player1.value - player2.value) {
    -1,2 -> 6
    0 -> 3
    else -> 0
}

fun main() {
    fun part1(input: List<String>): Int = input.sumOf { round ->
        val opponent = round.substringBefore(" ").toShape()
        val player = round.substringAfter(" ").toShape()
        draw(opponent, player) + player.value
    }

    fun part2(input: List<String>): Int = input.sumOf { round ->
        val opponent = round.substringBefore(" ").toShape()
        val player = round.substringAfter(" ").toStrategicShape(opponent)
        draw(opponent, player) + player.value
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day02_test")
    check(part1(testInput) == 15)
    check(part2(testInput) == 12)

    val input = readInput("Day02")
    println(part1(input))
    println(part2(input))
}