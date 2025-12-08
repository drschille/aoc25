package no.designsolutions.advent.app

import no.designsolutions.advent.utils.FileReader
import no.designsolutions.advent.utils.day_eight.JunctionMap
import no.designsolutions.advent.utils.day_eight.Segment

fun main() {
    val input = FileReader("day_eight_input.txt").text
    val junctionMap = JunctionMap(input)
    var sortedMap = junctionMap.mapDistances().toSortedMap { key1, key2 -> key1 compareTo key2 }

    var count = 0

    while (count < 1000) {
        try {
            val key = sortedMap.keys.toList().first()
            sortedMap[key]?.let {
                junctionMap.connect(it.first to it.second)
                count++
                sortedMap = sortedMap.filterNot { (_, value) ->
                    val innerVertices = junctionMap.elements.flatMap { it.internalVertices }
                    value.first in innerVertices ||
                            value.second in innerVertices
                }.toSortedMap()
                sortedMap = sortedMap.filterNot { (_, value) ->
                    val endPointPairs = junctionMap.elements.map { it.endVertices }
                    val segment = Segment(value.first, value.second)
                    endPointPairs.any { it.all { it in segment.vertices } }
                }.toSortedMap()
            }
        } catch (e: Exception) {
            println("Couldn't connect it because ${e.message}, still only $count connected")
        }
    }

    var productOfSegments = 1
    junctionMap.elements.take(3).forEach { productOfSegments = it.vertices.size.times(productOfSegments) }

    println("Product of three greatest elements: $productOfSegments")

    sortedMap.entries.take(3).forEach { (key, value) ->
        println("for key: $key, value: $value")
    }
}