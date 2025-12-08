package no.designsolutions.advent.utils.day_eight

class JunctionMap(input: String) {
    val junctions = input.lines().mapIndexed { index, line ->
        line.split(",").let { coordinates ->
            Vertex(index, Point(coordinates[0].toInt(), coordinates[1].toInt(), coordinates[2].toInt()))
        }
    }

    private val distanceMap = mutableMapOf<Float, Pair<Vertex, Vertex>>()
    val segments get() = elements.flatMap { it.segments }
    private val _elements = mutableListOf<Element>()
    val elements get() = _elements.toList().sortedByDescending { it.size }
    val chains: List<List<Int>>
        get() = elements.map { element ->
            val list = mutableListOf<Vertex>()
            element.endVertices.minByOrNull { it.id }!!.also { list.add(it) }
            val end = element.endVertices.maxByOrNull { it.id }!!
            while (end !in list) {
                val nextSegment = element.segments.first { list.last() in it.sortedById && !it.sortedById.all { it in list } }
                val next = nextSegment.sortedById.first { vertex -> vertex !in list }
                list.add(next)
            }
            list.map { it.id }
        }

    private val internalJunctions get() = _elements.flatMap { it.internalVertices }.toSet()

    fun mapDistances(): MutableMap<Float, Pair<Vertex, Vertex>> {
        distanceMap.clear()
        junctions.forEachIndexed { index, point ->
            if (point == junctions.last()) return@forEachIndexed
            for (i in index + 1..junctions.size - 1) {
                val other = junctions[i]
                val distance = point.point distanceToPoint other.point
                distanceMap.put(distance, point to other)
            }
        }
        return distanceMap
    }

    fun connect(vertices: Pair<Vertex, Vertex>) {
        val vList = vertices.toList()
        if (vList.any { it in internalJunctions }) throw IllegalStateException("Can't connect to internal junctions")
        if (elements.any {
                it.endVertices.all { it in vList }
            }) throw IllegalStateException("Can't close any loops")
        val segment = Segment(vertices.first, vertices.second)
        var newElement = Element(segments = listOf(segment))
        val listForDeletion = mutableListOf<Int>()
        _elements.forEach {
            if (it.canJoin(newElement)) {
                newElement = it.join(newElement)
                listForDeletion.add(it.id)
            }
        }
        val remainingElements = _elements.filter { it.id !in listForDeletion } + newElement
        _elements.clear()
        _elements.addAll(remainingElements)
    }


}