package no.designsolutions.advent.utils.day_eight

data class Element(val id: Int = nextId++, val segments: List<Segment>) {

    val vertices: Set<Vertex>
        get() = segments.flatMap { it.sortedById }.toSet()

    val internalVertices: Set<Vertex>
        get() = vertices.filter { vertex -> segments.count { it.start == vertex || it.end == vertex } >= 2 }
            .toSet()

    val endVertices: Set<Vertex>
        get() = vertices.filter { vertex -> vertex !in internalVertices }.toSet()

    fun canJoin(other: Element): Boolean =
        endVertices.any { it in other.endVertices } && !endVertices.all { it in other.endVertices }

    fun join(other: Element): Element {
        if (!other.endVertices.any { it in endVertices }) throw IllegalStateException("Can't join unconnected elements")
        if (other.endVertices.all { it in endVertices }) throw IllegalStateException("Can't make closed loops")
        val element = this.takeIf { it.id < other.id } ?: other
        return copy(id = element.id, segments = segments + other.segments)
    }

    val size get() = segments.size
    val numberOfVertices get() = vertices.size

    companion object {
        var nextId: Int = 0
    }
}
