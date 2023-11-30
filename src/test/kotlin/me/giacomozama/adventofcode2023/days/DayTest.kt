package me.giacomozama.adventofcode2023.days

import java.io.File
import kotlin.test.Test
import kotlin.test.assertEquals

class DayTest {

    private fun testDay(dayNumber: Int, expectedOutput1: Any, expectedOutput2: Any) {
        val dayNumberStr = dayNumber.toString().padStart(2, '0')
        val day = Class.forName(Day::class.qualifiedName + dayNumberStr).constructors[0].newInstance() as Day
        day.parseInput(File("input/Day$dayNumberStr.txt"))
        assertEquals(expectedOutput1, day.solveFirstPuzzle())
        assertEquals(expectedOutput2, day.solveSecondPuzzle())
    }

    @Test
    fun `test Day1`() {
    }
}