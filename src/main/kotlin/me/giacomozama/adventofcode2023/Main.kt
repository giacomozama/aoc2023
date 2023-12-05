package me.giacomozama.adventofcode2023

import me.giacomozama.adventofcode2023.days.*
import java.io.File
import kotlin.time.Duration
import kotlin.time.measureTime

fun main() {
    println("### Advent of Code 2023 - Giacomo Zama ###")

    val days = arrayOf(
        ::Day01,
        ::Day02,
        ::Day03,
        ::Day04,
//        ::Day05,
//        ::Day06,
//        ::Day07,
//        ::Day08,
//        ::Day09,
//        ::Day10,
//        ::Day11,
//        ::Day12,
//        ::Day13,
//        ::Day14,
//        ::Day15,
//        ::Day16,
//        ::Day17,
//        ::Day18,
//        ::Day19,
//        ::Day20,
//        ::Day21,
//        ::Day22,
//        ::Day23,
//        ::Day24,
//        ::Day25
    )

    var globalTiming = Duration.ZERO
    for (i in days.indices) {
        println("\n-- Solving day ${i + 1}:")
        val day: Day

        val instantiationTiming = measureTime { day = days[i]() }
        println("---- Instantiated in $instantiationTiming.")
        globalTiming += instantiationTiming

        val parsingTiming = measureTime {
            day.parseInput(File("input/Day${(i + 1).toString().padStart(2, '0')}.txt"))
        }
        println("---- Parsed input in $parsingTiming.")
        globalTiming += parsingTiming

        val firstSolution: Any
        val firstTiming = measureTime { firstSolution = day.solveFirstPuzzle() }
        println("---- First puzzle solution: $firstSolution. Took $firstTiming.")
        globalTiming += firstTiming

        val secondSolution: Any
        val secondTiming = measureTime { secondSolution = day.solveSecondPuzzle() }
        println("---- Second puzzle solution: $secondSolution. Took $secondTiming.")
        globalTiming += secondTiming
    }

    println("\n### All done! Took $globalTiming. ###")
}
