package com.dg.eqs.core.execution.intention

import com.dg.eqs.base.abbreviation.*
import com.dg.eqs.base.extension.first
import com.dg.eqs.core.definition.Parent
import com.dg.eqs.core.definition.term.Term
import com.dg.eqs.core.definition.term.Terms


sealed class Intention {
    class Link(
            val origin: AnyOrigin,
            val source: Terms,
            val target: Terms
    ) : Intention() {

        val firstSource: Term
        val firstTarget: Term
        val sourceParent: Parent
        val targetParent: Parent
        val mutualParent: Parent
        val parentsChain: Parents


        init {
            check(source.isNotEmpty())
            check(target.isNotEmpty())

            firstSource = source.first
            firstTarget = target.first

            val sourceParents = retrieveParents(origin, firstSource)
            val targetParents = retrieveParents(origin, firstTarget)

            check(sourceParents.isNotEmpty())
            check(targetParents.isNotEmpty())

            sourceParent = sourceParents.first
            targetParent = targetParents.first

            mutualParent = retrieveMutualParent(sourceParents, targetParents)
            parentsChain = retrieveParentsChain(sourceParents, targetParents)
        }

        constructor(origin: AnyOrigin, source: Term, target: Term)
                : this(origin, termsOf(source), termsOf(target))

        constructor(draft: AnyDraft, source: TermDraft, target: TermDraft)
                : this(draft.origin, termsOf(source.origin), termsOf(target.origin))

        constructor(draft: AnyDraft, source: TermDrafts, target: TermDrafts)
                : this(draft.origin, termsOf(source.origins), termsOf(target.origins))

        override fun equals(other: Any?): Boolean {
            if(this === other) return true
            if(javaClass != other?.javaClass) return false

            other as Link

            if(origin != other.origin) return false
            if(source != other.source) return false
            if(target != other.target) return false
            if(parentsChain != other.parentsChain) return false

            return true
        }

        override fun hashCode(): Int {
            var result = origin.hashCode()

            result = 31 * result + source.hashCode()
            result = 31 * result + target.hashCode()
            result = 31 * result + parentsChain.hashCode()

            return result
        }

        private fun retrieveParents(origin: AnyOrigin, child: Term)
                : Parents = buildList {

            if(origin is Parent && child in origin) {
                origin.forEach {
                    addAll(retrieveParents(it, child))
                }

                add(origin)
            }
        }

        private operator fun Parent.contains(child: Term): Boolean =
                any {
                    it === child || (it is Parent && child in it)
                }

        private fun retrieveMutualParent(sourceParents: Parents, targetParents: Parents): Parent {
            sourceParents.forEach { sourceParent ->
                targetParents.forEach { targetParent ->
                    if(targetParent === sourceParent) return targetParent
                }
            }

            throw IllegalStateException("No mutual parent of $source and $target found")
        }

        private fun retrieveParentsChain(sourceParents: Parents, targetParents: Parents): Parents {
            val sourceParentsUntilMutualOne = sourceParents.dropLastWhile { it !== mutualParent }.dropLast(1)
            val targetParentsUntilMutualOne = targetParents.dropLastWhile { it !== mutualParent }.dropLast(1)

            return sourceParentsUntilMutualOne + mutualParent + targetParentsUntilMutualOne.reversed()
        }
    }

    sealed class Click : Intention() {
        abstract val origin: AnyOrigin


        data class Invert(
                override val origin: AnyOrigin
        ) : Click()
    }
}