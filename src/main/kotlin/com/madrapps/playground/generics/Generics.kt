package com.madrapps.playground.generics

private interface Vehicle
private interface Drivable
private interface Sailable
private interface Flyable

private class Car : Vehicle, Drivable
private class Ship : Vehicle, Sailable
private class Jet : Vehicle, Flyable
private class BatMobile: Vehicle, Drivable, Sailable, Flyable

private class Road<T> {}
private class River<T : Vehicle>
private class Underground<out T>
private class Underwater<out T : Vehicle>
private class Space<in T>
private class Rails<in T : Vehicle>
private class Sky<out T> where T : Vehicle, T : Flyable
private class Sea<in T, out U> where T : Vehicle, T : Sailable, U: Vehicle
