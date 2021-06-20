package com.dg.eqs.core.generation

import kotlin.random.Random


class GenerationSelector(
        private val random: Random) {

    fun <T> selectOption(options: List<T>) =
            options[nextInt(options.size)]

    fun selectValue(rareValues: Set<Int>): Int =
            nextInt(10).takeIf {
                it !in rareValues
                        || nextDouble(1.0) <= 0.33
            } ?: selectValue(rareValues)

    private fun nextInt(until: Int) = random.nextInt(until)

    private fun nextDouble(until: Double) = random.nextDouble(until)
}