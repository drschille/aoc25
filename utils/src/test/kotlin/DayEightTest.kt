package no.designsolutions.advent.utils

import no.designsolutions.advent.utils.day_eight.JunctionMap
import no.designsolutions.advent.utils.day_eight.Point
import no.designsolutions.advent.utils.day_eight.Segment
import no.designsolutions.advent.utils.day_eight.Vertex
import java.util.SortedMap
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

internal class DayEightTest {
    lateinit var junctionMap: JunctionMap
    lateinit var sortedMap: SortedMap<Float, Pair<Vertex, Vertex>>

    @BeforeTest
    fun startup() {
        val raw = FileReader("day_eight_test.txt").text
        junctionMap = JunctionMap(raw)
        sortedMap = junctionMap.mapDistances().toSortedMap { key1, key2 ->
            key1 compareTo key2
        }
    }

    @Test
    fun `check the sorting by distance`() {
        val map = sortedMap
        assert(map.firstEntry().value.toList().map { it.point }.contains(Point(x = 162, y = 817, z = 812)))
        assert(map.firstEntry().value.toList().map { it.point }.contains(Point(x = 425, y = 690, z = 689)))
    }

    @Test
    fun `two elements with shared ends can merge`() {
        val vertexOne = junctionMap.junctions[0]
        val vertexTwo = junctionMap.junctions[1]
        val vertexThree = junctionMap.junctions[2]
        val vertexFour = junctionMap.junctions[3]

        assertEquals(0, junctionMap.elements.size)
        junctionMap.connect(vertexOne to vertexTwo)
        assertEquals(1, junctionMap.elements.size)
        junctionMap.connect(vertexThree to vertexFour)
        assertEquals(2, junctionMap.elements.size)
        junctionMap.connect(vertexTwo to vertexThree)
        assertEquals(1, junctionMap.elements.size)
        assertEquals(3, junctionMap.elements.first().segments.size)
    }

    @Test
    fun `merging inner vertices crashes`() {
        val vertexOne = junctionMap.junctions[0]
        val vertexTwo = junctionMap.junctions[1]
        val vertexThree = junctionMap.junctions[2]
        val vertexFour = junctionMap.junctions[3]

        junctionMap.connect(vertexOne to vertexTwo)
        junctionMap.connect(vertexTwo to vertexThree)

        var hasFailed = false
        try {
            junctionMap.connect(vertexTwo to vertexFour)
        } catch (e: IllegalStateException) {
            println(e.message)
            hasFailed = true
        }
        assert(hasFailed)
        assertEquals(2, junctionMap.segments.size)
    }

    @Test
    fun `closing loops should crash`() {
        val vertexOne = junctionMap.junctions[0]
        val vertexTwo = junctionMap.junctions[1]
        val vertexThree = junctionMap.junctions[2]

        junctionMap.connect(vertexOne to vertexTwo)
        junctionMap.connect(vertexTwo to vertexThree)
        var hasFailed = false
        try {
            junctionMap.connect(vertexOne to vertexThree)
        } catch (e: Exception) {
            println(e.message)
            hasFailed = true
        }
        assert(hasFailed)
        assertEquals(2, junctionMap.segments.size)
    }

    @Test
    fun `connect the first ten`() {
        var count = 0
        println(sortedMap.entries.take(20).map { (_, value) ->
            Segment(value.first, value.second).vertices.map { it.id }
        }.joinToString("\n"))
        while (count < 10) {
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
            println(junctionMap.chains)
//            println(junctionMap.elements.map {it.numberOfVertices})
//            println(junctionMap.elements.map {it.endVertices.map {it.id}})
//            index++
        }

        var productOfSegments = 1
        junctionMap.elements.take(3).forEach { productOfSegments = it.vertices.size.times(productOfSegments) }

        assertEquals(48, productOfSegments)
    }
}