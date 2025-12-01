package no.designsolutions.advent.utils

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.Serializable

@Serializable
class Printer(val message: String) {
    fun printMessage() = runBlocking {
        launch {
            delay(1000L)
        }
        println(message)
    }
}