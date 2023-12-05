package me.giacomozama.adventofcode2023.days

import java.io.File

class Day03 : Day() {

    private lateinit var input: List<String>

    override fun parseInput(inputFile: File) {
        input = inputFile.readLines()
    }

    override fun solveFirstPuzzle(): Int {
        fun isAdjacentToSymbol(y: Int, startX: Int, endX: Int): Boolean {
            fun checkLine(line: String): Boolean {
                if (startX > 0 && line[startX - 1] != '.') return true
                if (endX < input[y].length && line[endX] != '.') return true
                for (x in startX until endX) {
                    if (line[x] != '.') return true
                }
                return false
            }

            if (startX > 0 && input[y][startX - 1] != '.') return true
            if (endX < input[y].length && input[y][endX] != '.') return true
            return y > 0 && (checkLine(input[y - 1])) || y < input.lastIndex && checkLine(input[y + 1])
        }

        var result = 0

        for ((y, line) in input.withIndex()) {
            var acc = 0
            var startX = -1
            for ((x, c) in line.withIndex()) {
                if (c.isDigit()) {
                    if (startX == -1) {
                        startX = x
                        acc = c.digitToInt()
                    } else {
                        acc = acc * 10 + c.digitToInt()
                    }
                } else {
                    if (startX != -1) {
                        if (isAdjacentToSymbol(y, startX, x)) result += acc
                        acc = 0
                        startX = -1
                    }
                }
            }
            if (startX != -1 && isAdjacentToSymbol(y, startX, line.length)) result += acc
        }

        return result
    }

    override fun solveSecondPuzzle(): Int {
        val gearNumbers = hashMapOf<Int, MutableList<Int>>()

        fun mapStarNumber(y: Int, x: Int, value: Int) {
            gearNumbers.getOrPut(y * 1000 + x) { mutableListOf() }.add(value)
        }

        fun mapNearbyStars(y: Int, startX: Int, endX: Int, value: Int) {
            fun checkLine(lineY: Int) {
                val line = input[lineY]
                if (startX > 0 && line[startX - 1] == '*') mapStarNumber(lineY, startX - 1, value)
                if (endX < input[y].length && line[endX] == '*') mapStarNumber(lineY, endX, value)
                for (x in startX until endX) {
                    if (line[x] == '*') mapStarNumber(lineY, x, value)
                }
            }
            if (startX > 0 && input[y][startX - 1] == '*') mapStarNumber(y, startX - 1, value)
            if (endX < input[y].length && input[y][endX] == '*') mapStarNumber(y, endX, value)
            if (y > 0) checkLine(y - 1)
            if (y < input.lastIndex) checkLine(y + 1)
        }

        for ((y, line) in input.withIndex()) {
            var acc = 0
            var startX = -1
            for ((x, c) in line.withIndex()) {
                if (c.isDigit()) {
                    if (startX == -1) {
                        startX = x
                        acc = c.digitToInt()
                    } else {
                        acc = acc * 10 + c.digitToInt()
                    }
                } else {
                    if (startX != -1) {
                        mapNearbyStars(y, startX, x, acc)
                        acc = 0
                        startX = -1
                    }
                }
            }
            if (startX != -1) mapNearbyStars(y, startX, line.length, acc)
        }

        var result = 0

        for (values in gearNumbers.values) {
            if (values.size == 2) result += values[0] * values[1]
        }

        return result
    }
}