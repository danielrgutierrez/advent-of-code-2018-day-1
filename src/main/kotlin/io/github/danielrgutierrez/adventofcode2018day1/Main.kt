package io.github.danielrgutierrez.adventofcode2018day1

import java.io.File

fun main(args: Array<String>) {
    val frequencies = mutableListOf<Int>()

    try {
        File(args[0]).forEachLine(Charsets.UTF_8) { frequencies.add(it.toInt()) }
    }
    catch (e: Exception) {
        println("Usage: java -jar advent-of-code-2018-day-1-1.0.jar <input file>")
        System.exit(-1)
    }

    val resultingFrequency = calcResultingFrequency(frequencies)

    println("The resulting frequency is $resultingFrequency.")


    val firstFrequencyHitTwice = calcFirstFrequencyHitTwice(frequencies)

    println("The first frequency hit twice is $firstFrequencyHitTwice.")
}

fun calcResultingFrequency(frequencies: MutableList<Int>): Int = frequencies.sum()

fun calcFirstFrequencyHitTwice(frequencies: MutableList<Int>): Int {
    val resultingFrequencies = mutableMapOf<Int, Int>()
    var index = 0
    var sum = 0
    var firstFrequencyHitTwice: Int? = null
    while (firstFrequencyHitTwice == null) {
        sum += frequencies[index % frequencies.size]
        resultingFrequencies[sum] = resultingFrequencies[sum]?.let { it + 1 } ?: 1

        if (resultingFrequencies[sum]?.let { it > 1 } == true) {
            firstFrequencyHitTwice = sum
        }
        index++
    }
    return firstFrequencyHitTwice
}