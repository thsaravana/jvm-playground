package com.madrapps.playground.coroutines

import kotlinx.coroutines.*

fun main() = runBlocking {
    // Job cancel
    jobCancellation()
    println("\n\n")

    // Cooperative cancellation using isActive
    isActiveCancellation()
    println("\n\n")

    // Cooperative cancellation using yield()
    yieldCancellation()
}

private suspend fun jobCancellation() = coroutineScope {
    val job = launch {
        repeat(1000) {
            println("Job: Printing $it")
            delay(500L)
        }
    }
    delay(1800L)
    println("Job: Cancel now")
    job.cancel()
    job.join()
    // job.cancelAndJoin() // Or you can just use this.
    println("Job: Cancelled")
}

private suspend fun isActiveCancellation() = coroutineScope {
    var i = 0
    val job = launch {
        while (isActive) {
            println("IsActive: Printing ${i++}")
            delay(500L)
        }
    }
    delay(1800L)
    println("IsActive: Cancel now")
    job.cancelAndJoin()
    println("IsActive: Cancelled")
}

private suspend fun yieldCancellation() = coroutineScope {
    var i = 0
    val job = launch {
        while (i < 100) {
            yield()
            println("Yield: Printing ${i++}")
            delay(500L)
        }
    }
    delay(1800L)
    println("Yield: Cancel now")
    job.cancelAndJoin()
    println("Yield: Cancelled")
}
