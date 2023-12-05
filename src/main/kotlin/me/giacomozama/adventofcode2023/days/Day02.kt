package me.giacomozama.adventofcode2023.days

import java.io.File

class Day02 : Day() {

    private class Game(val id: Int, val rounds: List<Round>) {
        class Round(val red: Int, val green: Int, val blue: Int)
    }

    private lateinit var games: List<Game>

    override fun parseInput(inputFile: File) {
        val idRegex = """Game (\d+):""".toRegex()
        val games = mutableListOf<Game>()
        inputFile.forEachLine { line ->
            val id = idRegex.matchAt(line, 0)!!.groupValues[1].toInt()
            val rounds = line.substringAfter(": ").split("; ").map { roundStr ->
                var red = 0
                var green = 0
                var blue = 0
                for (token in roundStr.split(", ")) {
                    val amt = token.substringBefore(' ').toInt()
                    when (token.last()) {
                        'd' -> red = amt
                        'n' -> green = amt
                        'e' -> blue = amt
                    }
                }
                Game.Round(red, green, blue)
            }
            games.add(Game(id, rounds))
        }
        this.games = games
    }

    override fun solveFirstPuzzle(): Int {
        val maxRed = 12
        val maxGreen = 13
        val maxBlue = 14

        var result = 0

        games@ for (game in games) {
            for (round in game.rounds) {
                if (round.red > maxRed || round.green > maxGreen || round.blue > maxBlue) continue@games
            }
            result += game.id
        }

        return result
    }

    override fun solveSecondPuzzle(): Int {
        var result = 0

        for (game in games) {
            var minRed = 0
            var minGreen = 0
            var minBlue = 0

            for (round in game.rounds) {
                minRed = maxOf(minRed, round.red)
                minGreen = maxOf(minGreen, round.green)
                minBlue = maxOf(minBlue, round.blue)
            }

            result += minRed * minGreen * minBlue
        }

        return result
    }
}