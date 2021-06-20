package com.dg.eqs.core.definition.term.item

import com.dg.eqs.core.definition.term.Term


abstract class Item : Term() {
    override fun isSolved() = true

    override fun remove(term: Term): Item {
        require(term !== this)

        return this
    }

    override fun replace(old: Term, new: Term) =
            if(old === this) new else this

    override fun resolve() = this
}