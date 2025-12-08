package no.designsolutions.advent.utils.day_eight

data class Segment(val start: Vertex, val end: Vertex) {
    val length = start.point distanceToPoint end.point
    val vertices = listOf(start, end)
    val sortedById = vertices.sortedBy { it.id }
}
