package main.kotlin

import java.lang.Exception

sealed class Shape(
    val name: String,
    val value: Int
)

object Rock: Shape("Rock", 1)
object Paper: Shape("Paper", 2)
object Scissors: Shape("Scissors", 3)

fun Shape.trumps() = when (this) {
    is Rock -> Scissors
    is Paper -> Rock
    is Scissors -> Paper
}

fun Shape.losesTo() = when (this) {
    is Rock -> Paper
    is Paper -> Scissors
    is Scissors -> Rock
}

sealed class ShapeException(message: String) : Exception(message) {
    object InvalidShapeException : ShapeException("Invalid shape")
}