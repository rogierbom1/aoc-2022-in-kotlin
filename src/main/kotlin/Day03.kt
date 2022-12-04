package main.kotlin

import readInput

private fun Char.toPriority(): Int {
    val priorityMap = ('a'..'z').associateWith { it - 'a' + 1 } + ('A'..'Z').associateWith { it - 'A' + 27 }
    return priorityMap[this] ?: throw Exception("Unmapped character")
}

fun main() {
    fun part1(input: List<String>): Int = input.map{ rucksack ->
            rucksack.take(rucksack.length / 2).toSet() to rucksack.substring(rucksack.length / 2).toSet()
        }.mapNotNull { compartments ->
            compartments.first.intersect(compartments.second).firstOrNull()
        }.sumOf { item ->
            item.toPriority()
        }

    fun part2(input: List<String>): Int = input.chunked(3).mapNotNull { rucksacks ->
            rucksacks[0].toList().intersect(rucksacks[1].toSet()).intersect(rucksacks[2].toSet()).firstOrNull()
        }.sumOf { badge ->
            badge.toPriority()
        }

    val testInput = readInput("Day03_test")
    check(part1(testInput) == 157)
    check(part2(testInput) == 70)

    val input = readInput("Day03")
    println(part1(input))
    println(part2(input))
}