package me.giacomozama.adventofcode2023.days

import java.io.File

class Day01 : Day() {

    private lateinit var input: List<String>

    override fun parseInput(inputFile: File) {
        input = inputFile.readLines()
    }

    override fun solveFirstPuzzle(): Int {
        var result = 0
        for (line in input) {
            var lineResult = 0
            for (c in line) {
                if (c.isDigit()) {
                    lineResult = c.digitToInt() * 10
                    break
                }
            }
            for (i in line.lastIndex downTo 0) {
                val c = line[i]
                if (c.isDigit()) {
                    lineResult += c.digitToInt()
                    break
                }
            }
            result += lineResult
        }
        return result
    }

    override fun solveSecondPuzzle(): Int {
        fun findFirstDigit(line: String, names: List<String>): Int {
            for (i in line.indices) {
                val possible = names.toMutableList()
                var j = i
                while (possible.size > 1 || possible.size == 1 && j - i < possible[0].length) {
                    val c = line[j++]
                    if (c.isDigit()) return c.digitToInt()
                    val l = j - i - 1
                    possible.removeIf { it.length <= l || it[l] != c }
                }
                if (possible.size == 1) return names.indexOf(possible[0])
            }
            throw IllegalArgumentException()
        }

        val names = listOf("zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine")
        val reversedNames = names.map { it.reversed() }
        return input.sumOf { findFirstDigit(it, names) * 10 + findFirstDigit(it.reversed(), reversedNames) }
    }
}