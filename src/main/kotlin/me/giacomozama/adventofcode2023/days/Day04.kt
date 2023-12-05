package me.giacomozama.adventofcode2023.days

import java.io.File

class Day04 : Day() {

    private class Card(val winning: List<Int>, val ownNumbers: List<Int>)

    private lateinit var cards: List<Card>

    override fun parseInput(inputFile: File) {
        fun extractNumbers(data: String) = data
            .substringBefore(" | ")
            .split(' ')
            .filterNot { it.isEmpty() }
            .map { it.toInt() }

        val cards = mutableListOf<Card>()
        inputFile.forEachLine { line ->
            val data = line.substringAfter(": ")
            cards.add(
                Card(
                    winning = extractNumbers(data.substringBefore(" | ")),
                    ownNumbers = extractNumbers(data.substringAfter(" | "))
                )
            )
        }

        this.cards = cards
    }

    override fun solveFirstPuzzle(): Int {
        var result = 0
        for (card in cards) {
            val won = card.ownNumbers.count { it in card.winning }
            if (won > 0) result += 1 shl (won - 1)
        }
        return result
    }

    override fun solveSecondPuzzle(): Int {
        val copies = IntArray(cards.size) { 1 }
        var result = 0
        for ((i, card) in cards.withIndex()) {
            result += copies[i]
            for (j in i + 1 until i + 1 + card.ownNumbers.count { it in card.winning }) {
                if (j < cards.size) copies[j] += copies[i]
            }
        }
        return result
    }
}