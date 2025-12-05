package no.designsolutions.advent.utils

class FileReader(filename: String) {
    val text : String = ClassLoader.getSystemResourceAsStream(filename)?.bufferedReader()?.use { it.readText() }
        ?: error("couldn't read any data from file")
}