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
        testDay(1, 55607, 55291)
    }

    @Test
    fun `test Day2`() {
        testDay(2, 2476, 54911)
    }

    @Test
    fun `test Day3`() {
        testDay(3, 537732, 84883664)
    }

    @Test
    fun `test Day4`() {
        testDay(4, 23678, 15455663)
    }
}