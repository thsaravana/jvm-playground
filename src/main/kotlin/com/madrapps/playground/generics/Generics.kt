package com.madrapps.playground.generics

private interface Vehicle : Drivable, Sailable, Flyable
private interface Drivable
private interface Sailable
private interface Flyable

private class Car : Vehicle

// TODO Without variance
/*
private interface Land<T> {
    fun get(): T
    fun set(t: T)
}

private class Road : Land<Drivable> {
    private lateinit var drivable: Drivable

    override fun get(): Drivable {
        return drivable
    }

    override fun set(t: Drivable) {
        drivable = t
    }
}

fun main() {
    val road: Land<Drivable> = Road()
    road.set(Car())
    road.get()
//    val rail: Land<Vehicle> = road // Not possible
}
*/

// TODO Contravariance
/*
private interface Land<in T> {
//    fun get(): T // Not Possible
    fun set(t: T)
}

private class Road : Land<Drivable> {
    private lateinit var drivable: Drivable

    override fun set(t: Drivable) {
        drivable = t
    }
}

fun main() {
    val road: Land<Drivable> = Road()
    road.set(Car())
    val rail: Land<Vehicle> = road
//    val d: Land<Drivable> = rail // Not possible
}
*/

// TODO Covariance
/*
private interface Land<out T> {
    fun get(): T
//    fun set(t: T) // Not Possible
}

private class Road : Land<Vehicle> {
   private lateinit var vehicle: Vehicle

    override fun get(): Vehicle {
        return vehicle
    }
}

fun main() {
    val road: Land<Vehicle> = Road()
    road.get()
    val rail: Land<Drivable> = road
//    val d: Land<Vehicle> = rail // Not possible
}
*/
