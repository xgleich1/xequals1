package com.dg.eqs.core.definition.term

import com.dg.eqs.core.definition.term.operation.alteration.condensingstep.CondensingSide
import com.dg.eqs.core.definition.term.operation.dotoperation.division.alteration.DivisionThroughZeroException


class Terms(private val inner: List<Term>)
    : List<Term> by inner, CondensingSide {

    override fun equals(other: Any?): Boolean {
        if(this === other) return true
        if(javaClass != other?.javaClass) return false

        other as Terms

        if(inner != other.inner) return false

        return true
    }

    override fun hashCode() = inner.hashCode()

    override fun toString() = inner.toString()

    operator fun plus(term: Term) = Terms(inner + term)

    operator fun plus(terms: Terms) = Terms(inner + terms)

    override fun contains(element: Term) = indexOf(element) != -1

    operator fun contains(elements: Terms) = elements.all { contains(it) }

    override fun indexOf(element: Term) = indexOf { it === element }

    inline fun <reified T : Term> indexOf() = indexOf { it is T }

    inline fun <reified T : Term> lastIndexOf() = lastIndexOf { it is T }

    fun indexOf(predicate: (Term) -> Boolean): Int {
        forEachIndexed { i, term ->
            if(predicate(term)) return i
        }

        return -1
    }

    fun lastIndexOf(predicate: (Term) -> Boolean): Int {
        var lastIndex = -1

        forEachIndexed { i, term ->
            if(predicate(term)) lastIndex = i
        }

        return lastIndex
    }

    fun insert(index: Int, terms: Terms) = Terms(
            toMutableList()
                    .apply { addAll(index, terms) }
                    .toList())

    fun remove(term: Term) = filter { it !== term }

    fun remove(terms: Terms) = filter { it !in terms }

    fun replace(old: Term, new: Term) = map { if(it === old) new else it }

    fun map(transform: (Term) -> Term) = Terms(inner.map(transform))

    fun mapRemove(term: Term) = map { it.remove(term) }

    fun mapReplace(old: Term, new: Term) = map { it.replace(old, new) }

    fun mapInvert() = map { it.invert() }

    fun mapCopy() = map { it.copy() }

    @Throws(DivisionThroughZeroException::class)
    fun mapResolve() = map { it.resolve() }

    fun allOnes() = all(Term::isOne)

    fun allZeros() = all(Term::isZero)

    fun allConstants() = all(Term::isConstant)

    private fun filter(predicate: (Term) -> Boolean) = Terms(inner.filter(predicate))
}