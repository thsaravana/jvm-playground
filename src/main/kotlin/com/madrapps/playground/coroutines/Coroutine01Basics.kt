package com.madrapps.playground.coroutines

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    // Suspend function
    println("Suspend: I go first")
    launch { printNext() }
    println("Suspend: I go next")
    delay(2000L)
    println("\n\n")

    // coroutineScope builder
    runMultipleJobs()
    println("CoroutineScope: Jobs done")
    println("\n\n")

    // Job join
    val job = launch {
        println("Job: Start")
        delay(1000L)
        println("Job: End")
    }
    println("Job: Job done?")
    job.join()
    println("Job: Job done!")
}

private suspend fun printNext() {
    println("Suspend: I go after you")
    delay(1000L)
    println("Suspend: I go last :(")
}

suspend fun runMultipleJobs() = coroutineScope {
    println("CoroutineScope: Start")
    launch {
        delay(1000L)
        println("CoroutineScope: I am delayed by 1000ms")
    }
    launch {
        delay(500L)
        println("CoroutineScope: I am delayed by 500ms")
    }
    println("CoroutineScope: End")
}
