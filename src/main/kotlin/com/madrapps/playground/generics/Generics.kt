package com.madrapps.playground.generics

private interface Animal
private interface Walkable

private class Cat: Animal

private class InVariant {

    class Jungle<T>(private var t: T) {
        fun obtainType(): T {
            return t
        }
    }

    fun main() {
        val jungle: Jungle<Animal> = Jungle(Cat())
//        val jungleError: Jungle<Any> = jungle // Compile error
    }
}

private class DeclarationSite {

    private class CoVariant {

        class Jungle<out T>(val t: T) {
            fun obtainType(): T {
                return t
            }
            // fun insertType(t: T) {} // Not Possible (Type parameter T is declared as 'out', so no Write operation on type T)
        }

        fun main() {
            val jungleWithAnimals: Jungle<Animal> = Jungle(Cat())
            val jungleWithAnything: Jungle<Any> = jungleWithAnimals // This won't be possible if you remove the `out`
        }
    }

    private class ContraVariant {

        class Jungle<in T>(private var t: T) {
//            fun obtainType(): T { // Not Possible (Type parameter T is declared as 'int', so no Read operation on type T)
//                return t
//            }
             fun insertType(t: T) {}
        }

        fun main() {
            val jungleWithAnimals: Jungle<Any> = Jungle(Cat())
            val jungleWithAnything: Jungle<Animal> = jungleWithAnimals // This won't be possible if you remove the `out`
        }
    }
}

private class UseSite {

    class Jungle<T>(private var t: T) {
        fun obtainType(): T {
            return t
        }
        fun insertType(t: T) {}
    }

    private class CoVariant {

        fun main() {
            val jungleWithAnimals: Jungle<Animal> = Jungle(Cat())
            val jungleWithAnything: Jungle<out Any> = jungleWithAnimals
//    val type: Animal = jungleWithAnything.obtainType() // Not possible. You can only Read 'Any'
            val type: Any = jungleWithAnything.obtainType()
//    jungleWithAnything.insertType(Cat()) // Not possible. You can't Write.
        }
    }

    private class ContraVariant {

        fun main() {
            val jungleWithAnimals: Jungle<Any> = Jungle(Cat())
            val jungleWithAnything: Jungle<in Animal> = jungleWithAnimals
            val type: Any? = jungleWithAnything.obtainType()
            jungleWithAnything.insertType(Cat())
        }
    }
}
