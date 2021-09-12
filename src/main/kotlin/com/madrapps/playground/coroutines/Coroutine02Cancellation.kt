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
    println("\n\n")

    // Finally while cancellation
    finallyCancellation()
    println("\n\n")
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

private suspend fun finallyCancellation() = coroutineScope {
    val job = launch {
        try {
            repeat(100) {
                println("Finally: Printing $it")
                delay(500L)
            }
        } finally {
            withContext(NonCancellable) {
                println("Finally: Final non cancellable block start")
                delay(1000L)
                println("Finally: Final non cancellable block end")
            }
            println("Finally: Final block start")
            delay(1000L)
            println("Finally: Final block end")
        }
    }
    delay(1800L)
    println("Finally: Cancel now")
    job.cancelAndJoin()
    println("Finally: Cancelled")
}
