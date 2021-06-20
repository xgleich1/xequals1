package com.dg.eqs.base.extension


val <T> List<T>.single get() = single()

val <T> List<T>.first get() = component1()

val <T> List<T>.second get() = component2()

val <T> List<T>.third get() = component3()

val <T> List<T>.fourth get() = component4()

val <T> List<T>.last get() = last()

val <T> List<T>.isSingle get() = size == 1

val <T> List<T>.isNotSingle get() = !isSingle


fun <T> List<T>.mix(other: List<T>): List<T> {
    require(other.size == size - 1)

    return zip(other).flatMap { it.toList() } + last
}

fun <T> List<T>.findDistinctPair(predicate: Pair<T, T>.() -> Boolean): Pair<T, T>? {
    onEachDistinctPair {
        if(it.predicate()) return it
    }

    return null
}

fun <T> List<T>.containsDistinctPair(predicate: Pair<T, T>.() -> Boolean) =
        findDistinctPair(predicate) != null

fun <T> List<T>.contentEqualsByIdentity(other: List<T>): Boolean {
    if(size != other.size) return false

    forEachIndexed { index, element ->
        if(element !== other[index]) return false
    }

    return true
}

inline fun <reified A> List<Any?>.isMonad() =
        size == 1 && first is A

inline fun <reified A, reified B> List<Any?>.isDyad() =
        size == 2 && first is A && second is B

inline fun <reified A, reified B, reified C> List<Any?>.isTriad() =
        size == 3 && first is A && second is B && third is C

inline fun <reified T> List<Any?>.any() = any { it is T }

inline fun <reified T> List<Any?>.all() = all { it is T }

inline fun <reified T> List<Any?>.find() = find { it is T } as T

inline fun <reified T> List<Any?>.findLast() = findLast { it is T } as T

inline fun <reified T> List<Any?>.typedSingle() = single as T

inline fun <reified T> List<Any?>.typedFirst() = first as T

inline fun <reified T> List<Any?>.typedSecond() = second as T

private inline fun <T> List<T>.onEachDistinctPair(action: (Pair<T, T>) -> Unit) {
    for(i in 0..lastIndex) {
        for(j in (i + 1)..lastIndex) {
            action(get(i) and get(j))
        }
    }
}