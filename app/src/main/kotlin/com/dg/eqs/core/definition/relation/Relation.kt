package com.dg.eqs.core.definition.relation

import com.dg.eqs.core.definition.Origin
import com.dg.eqs.core.definition.Parent
import com.dg.eqs.core.definition.relation.alteration.RelationAlteration
import com.dg.eqs.core.definition.term.Term
import com.dg.eqs.core.definition.term.Terms
import com.dg.eqs.core.definition.term.item.variable.PositiveVariable


abstract class Relation(val left: Term, val right: Term) : Origin<Relation>, Parent {
    protected abstract val alteration: RelationAlteration


    override fun equals(other: Any?): Boolean {
        if(this === other) return true
        if(javaClass != other?.javaClass) return false

        other as Relation

        if(left != other.left) return false
        if(right != other.right) return false

        return true
    }

    override fun hashCode() = 31 * left.hashCode() + right.hashCode()

    override fun toString() = "[$left, $right]"

    override fun isSolved() = when {
        isLeftTheExemptedVariable() -> right.isSolved()
        isRightTheExemptedVariable() -> left.isSolved()

        else -> false
    }

    override fun remove(term: Term) = alteration.remove(this, term)

    override fun replace(old: Term, new: Term) = alteration.replace(this, old, new)

    override fun copy() = recreate(left.copy(), right.copy())

    override fun any(predicate: (Term) -> Boolean) = predicate(left) || predicate(right)

    override fun forEach(action: (Term) -> Unit) {
        action(left)
        action(right)
    }

    abstract fun recreate(newLeft: Term, newRight: Term): Relation

    override fun invert() = recreate(left.invert(), right.invert())

    operator fun component1() = left

    operator fun component2() = right

    fun shift(source: Terms, target: Term): Relation {
        require(target === right || target === left)

        return alteration.shift(this, source, target)
    }

    private fun isLeftTheExemptedVariable() = left is PositiveVariable && right.isConstant()

    private fun isRightTheExemptedVariable() = right is PositiveVariable && left.isConstant()
}