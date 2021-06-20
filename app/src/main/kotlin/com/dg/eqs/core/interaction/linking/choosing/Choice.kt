package com.dg.eqs.core.interaction.linking.choosing

import com.dg.eqs.base.abbreviation.AnyDraft
import com.dg.eqs.base.abbreviation.TermDraft
import com.dg.eqs.base.abbreviation.TermDrafts
import com.dg.eqs.base.abbreviation.draftsOf
import kotlin.properties.Delegates.observable


class Choice(initialContent: TermDrafts = draftsOf()) {
    val choosableParent get() = content.firstOrNull()?.choosableParent
    val choosableSuccessor get() = content.lastOrNull()?.choosableSuccessor
    val choosablePredecessor get() = content.firstOrNull()?.choosablePredecessor

    var content by observable(initialContent) { _, _, new -> publish(new) }
        private set

    private val observers = arrayListOf<(TermDrafts) -> Unit>()


    override fun equals(other: Any?): Boolean {
        if(this === other) return true
        if(javaClass != other?.javaClass) return false

        other as Choice

        if(content != other.content) return false

        return true
    }

    override fun hashCode() = content.hashCode()

    override fun toString() = content.toString()

    fun clear() {
        content = draftsOf()
    }

    fun addInitialChosen(chosen: TermDraft) {
        content = draftsOf(chosen)
    }

    fun addEnsuingChosen(chosen: TermDraft) {
        content = when {
            chosen === choosableParent -> draftsOf(chosen)
            chosen === choosableSuccessor -> draftsOf(content, chosen)
            chosen === choosablePredecessor -> draftsOf(chosen, content)

            else -> throw IllegalArgumentException()
        }
    }

    fun observe(observer: (TermDrafts) -> Unit) {
        observers += observer
    }

    fun isNotEmpty() = content.isNotEmpty()

    infix fun absentIn(draft: AnyDraft) = content.all { it !in draft }

    operator fun contains(draft: TermDraft) = content.any { draft in it }

    private fun publish(newChoice: TermDrafts) = observers.forEach { it(newChoice) }
}