package com.madrapps.playground.coroutines

import kotlinx.coroutines.*

fun main() = runBlocking {
    // withTimeout
    withTimeoutNormal()
    println("\n\n")
}

private suspend fun withTimeoutNormal() = coroutineScope {
    try {
        withTimeout(1800L) {
            repeat(100) {
                println("withTimeoutNormal: Printing $it")
                delay(500L)
            }
        }
    } catch (e: TimeoutCancellationException) {
        println("withTimeoutNormal: Exception thrown")
    }
    // You could use withTimeoutOrNull() which doesn't throw an exception
}
